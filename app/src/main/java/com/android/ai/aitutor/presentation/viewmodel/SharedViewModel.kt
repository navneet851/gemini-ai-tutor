package com.android.ai.aitutor.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ai.aitutor.UiState
import com.android.ai.aitutor.domain.entities.Chat
import com.android.ai.aitutor.domain.entities.Conversation
import com.android.ai.aitutor.domain.usecases.SendPromptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor (
    private val sendPromptUseCase: SendPromptUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow()


    private val _currentConversation : MutableStateFlow<List<Conversation>> = MutableStateFlow(listOf(
            Conversation(
                peer = "AI",
                chat = Chat(
                    text = "Hello! How can I assist you today?"
                )
            )
        )
    )

    val currentConversation : StateFlow<List<Conversation>> = _currentConversation.asStateFlow()

    private fun addConversation(conversation: Conversation){
        _currentConversation.value += conversation
    }

    fun sendPrompt(
        prompt: String
    ) {
        addConversation(
            Conversation(
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
                    Conversation(
                        peer = "AI",
                        chat = response
                    )
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}