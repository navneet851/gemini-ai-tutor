package com.android.ai.aitutor.domain.usecases

import com.android.ai.aitutor.domain.entities.Chat
import com.android.ai.aitutor.domain.repository.Repository
import javax.inject.Inject

class SendPromptUseCase @Inject constructor(
    private val repository : Repository
) {
    suspend operator fun invoke(chat: Chat) : Chat{
        return repository.sendPrompt(chat)
    }
}