package com.android.ai.aitutor.presentation.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.android.ai.aitutor.R

sealed class Routes(
    val label : String,
    @DrawableRes val selectedIcon : Int,
    @DrawableRes val unSelectedIcon : Int,
    val route : String
) {
    object Home : Routes( label = "Home", selectedIcon = R.drawable.home_fill, unSelectedIcon = R.drawable.home, "home")
    object Chat : Routes( label = "Chat", selectedIcon = R.drawable.chat_fill, unSelectedIcon = R.drawable.chat, "chat")
    object History : Routes( label = "History", selectedIcon = R.drawable.history_fill, unSelectedIcon = R.drawable.history, "history")
}