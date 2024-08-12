package com.android.ai.aitutor.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ai.aitutor.MyApplication
import com.android.ai.aitutor.UiState
import com.android.ai.aitutor.domain.entities.Chat
import com.android.ai.aitutor.domain.entities.Conversation
import com.android.ai.aitutor.domain.entities.Peer
import com.android.ai.aitutor.domain.usecases.SendPromptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val sendPromptUseCase: SendPromptUseCase
) : ViewModel() {

    private val conversationDao = MyApplication.conversationDB.getConversationDao()



    private val _currentConversation: MutableStateFlow<List<Peer>> = MutableStateFlow(
        listOf(
            Peer(
                peer = "AI",
                chat = Chat(
                    text = "Hello! How can I assist you today?"
                )
            )
        )
    )
    val currentConversation: StateFlow<List<Peer>> = _currentConversation.asStateFlow()



    private val _conversations: MutableStateFlow<List<Conversation>> = MutableStateFlow(emptyList())
    val conversations: StateFlow<List<Conversation>> = _conversations.asStateFlow()


    init {
        getAllConversations()
    }

    private fun getCurrentDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss  yyyy-MM-dd")
        return current.format(formatter)
    }

    fun addConversation() {
        viewModelScope.launch(Dispatchers.IO) {
            val getTime = getCurrentDateTime()
            val recentConversation = _currentConversation.value
            conversationDao.addConversation(
                Conversation(
                    title = recentConversation.last().chat.text,
                    time = getTime,
                    conversation = recentConversation
                )
            )
            _currentConversation.value = listOf(
                Peer(
                    peer = "AI",
                    chat = Chat(
                        text = "Hello! How can I assist you today?"
                    )
                )
            )
        }

    }

    fun deleteConversation(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            conversationDao.deleteConversation(id)
        }
    }

    private fun getAllConversations() {
        viewModelScope.launch(Dispatchers.IO) {
            conversationDao.getAllConversations().collect{
                _conversations.value = it
            }
        }
    }

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow()


    private fun addConversation(conversation: Peer) {
        _currentConversation.value += conversation
    }

    fun sendConversationPrompt(
        prompt: String
    ) {
        addConversation(
            Peer(
                peer = "User",
                chat = Chat(text = prompt)
            )
        )
        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = sendPromptUseCase.invoke(Chat(text = prompt))
                _uiState.value = UiState.Success(response.text)
                addConversation(
                    Peer(
                        peer = "AI",
                        chat = response
                    )
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "")
            }
        }
    }

    fun sendPrompt(
        prompt: String
    ) {

        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = sendPromptUseCase.invoke(Chat(text = prompt))
                _uiState.value = UiState.Success(response.text)

            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}