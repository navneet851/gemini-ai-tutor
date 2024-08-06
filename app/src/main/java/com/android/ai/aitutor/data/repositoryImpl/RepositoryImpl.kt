package com.android.ai.aitutor.data.repositoryImpl

import com.android.ai.aitutor.domain.entities.Chat
import com.android.ai.aitutor.domain.entities.Conversation
import com.android.ai.aitutor.domain.repository.Repository

class RepositoryImpl : Repository {
    override suspend fun getConversations(): List<Conversation> {
        TODO("Not yet implemented")
    }

    override suspend fun sendPrompt(chat: Chat) {
        TODO("Not yet implemented")
    }

}