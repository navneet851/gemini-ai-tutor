package com.android.ai.aitutor.domain.entities

import androidx.room.Entity

@Entity
data class User(
    val id : Int,
    val name : String,
    val gender : String,
){
    constructor() : this( 1, "", "")
}