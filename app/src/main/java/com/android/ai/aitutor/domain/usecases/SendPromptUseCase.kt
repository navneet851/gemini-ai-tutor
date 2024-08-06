package com.android.ai.aitutor.domain.usecases

import com.android.ai.aitutor.domain.entities.Chat
import com.android.ai.aitutor.domain.repository.Repository

class SendPromptUseCase(private val repository : Repository) {
    suspend operator fun invoke(chat: Chat) {
        repository.sendPrompt(chat)
    }
}