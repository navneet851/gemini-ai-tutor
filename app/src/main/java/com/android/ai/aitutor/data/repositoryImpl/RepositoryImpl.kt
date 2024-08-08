package com.android.ai.aitutor.data.repositoryImpl

import com.android.ai.aitutor.BuildConfig
import com.android.ai.aitutor.domain.entities.Chat
import com.android.ai.aitutor.domain.repository.Repository
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content

class RepositoryImpl : Repository {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apiKey
    )


    override suspend fun sendPrompt(chat: Chat) : Chat{
        val response =  generativeModel.generateContent(
            content {
                //image(bitmap)
                text(chat.text)
            }
        )
        return Chat(text = response.text ?: "")

    }

}