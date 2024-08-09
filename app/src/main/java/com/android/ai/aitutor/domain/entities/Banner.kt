package com.android.ai.aitutor.domain.entities

import androidx.compose.ui.graphics.Color

data class Banner(
    val title: String,
    val iconId: Int,
    val darkColor: Color,
    val mediumColor: Color,
    val lightColor: Color
)