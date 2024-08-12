package com.android.ai.aitutor.domain.entities

data class History(
    val dateAndTime : String,
    val topic : String
) {
    constructor() : this("", "")
}