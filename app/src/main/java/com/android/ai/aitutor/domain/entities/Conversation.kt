package com.android.ai.aitutor.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Conversation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val time : String,
    val conversation : List<Peer>

)


