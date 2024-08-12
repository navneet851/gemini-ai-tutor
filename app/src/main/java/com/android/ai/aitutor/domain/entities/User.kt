package com.android.ai.aitutor.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val name : String,
    val gender : String,
){
    constructor() : this( 1, "", "")
}