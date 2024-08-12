package com.android.ai.aitutor

import android.app.Application
import androidx.room.Room
import com.android.ai.aitutor.data.datastore.local.ConversationDB
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object{
        lateinit var conversationDB : ConversationDB
    }

    override fun onCreate() {
        super.onCreate()

        conversationDB = Room.databaseBuilder(
            applicationContext,
            ConversationDB::class.java,
            ConversationDB.DATABASE_NAME
        ).build()
    }
}
