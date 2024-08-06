package com.android.ai.aitutor.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ai.aitutor.BuildConfig
import com.android.ai.aitutor.UiState
import com.android.ai.aitutor.domain.entities.Chat
import com.android.ai.aitutor.domain.entities.Conversation
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
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

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apiKey
    )

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
                val response = generativeModel.generateContent(
                    content {
                        //image(bitmap)
                        text(prompt)
                    }
                )
                response.text?.let { outputContent ->
                    _uiState.value = UiState.Success(outputContent)
                    addConversation(
                        Conversation(
                            peer = "AI",
                            chat = Chat(text = outputContent)
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "")
            }
        }
    }
}