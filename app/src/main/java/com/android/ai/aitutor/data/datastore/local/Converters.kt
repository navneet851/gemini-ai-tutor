package com.android.ai.aitutor.data.datastore.local

import androidx.room.TypeConverter
import com.android.ai.aitutor.domain.entities.Peer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromPeerList(value: List<Peer>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toPeerList(value: String): List<Peer> {
        val listType = object : TypeToken<List<Peer>>() {}.type
        return Gson().fromJson(value, listType)
    }
}