package com.android.ai.aitutor.domain.repository

import com.android.ai.aitutor.domain.entities.Chat
import com.android.ai.aitutor.domain.entities.Conversation

interface Repository {
    suspend fun getConversations(): List<Conversation>
    suspend fun sendPrompt(chat: Chat) : Chat
}