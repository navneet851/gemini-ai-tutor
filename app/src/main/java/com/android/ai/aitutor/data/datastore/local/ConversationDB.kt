package com.android.ai.aitutor.data.datastore.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.ai.aitutor.data.datastore.local.conversation.ConversationDao
import com.android.ai.aitutor.data.datastore.local.user.UserDao
import com.android.ai.aitutor.domain.entities.Conversation

@Database(entities = [Conversation::class], version = 1)
@TypeConverters(Converters::class)
abstract class ConversationDB : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "conversation_db"

    }

    abstract fun getConversationDao(): ConversationDao
    abstract fun getUserDao() : UserDao
}