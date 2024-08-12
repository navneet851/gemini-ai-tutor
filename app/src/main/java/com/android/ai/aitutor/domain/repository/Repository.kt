package com.android.ai.aitutor.domain.repository

import com.android.ai.aitutor.domain.entities.Chat

interface Repository {
//    suspend fun getConversations(): List<Conversation>
    suspend fun sendPrompt(chat: Chat) : Chat

}