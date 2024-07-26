package com.android.ai.aitutor.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.ai.aitutor.presentation.ui.screens.ChatScreen
import com.android.ai.aitutor.presentation.ui.screens.HistoryScreen
import com.android.ai.aitutor.presentation.ui.screens.HomeScreen

@Composable
fun MyNavHost(navController : NavHostController) {

    NavHost(
        navController,
        startDestination = Routes.Home.route
    ){
        composable(Routes.Home.route){
            HomeScreen()
        }
        composable(Routes.Chat.route){
            ChatScreen()
        }
        composable(Routes.History.route){
            HistoryScreen()
        }
    }
}