package com.android.ai.aitutor

import android.app.Application
import androidx.room.Room
import com.android.ai.aitutor.data.datastore.local.AppDB
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object{
        lateinit var appDB : AppDB
    }

    override fun onCreate() {
        super.onCreate()

        appDB = Room.databaseBuilder(
            applicationContext,
            AppDB::class.java,
            AppDB.DATABASE_NAME
        ).build()
    }
}
