package com.android.ai.aitutor.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.ai.aitutor.presentation.ui.screens.ChatScreen
import com.android.ai.aitutor.presentation.ui.screens.HistoryScreen
import com.android.ai.aitutor.presentation.ui.screens.HomeScreen
import com.android.ai.aitutor.presentation.ui.screens.InfoScreen
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel


@Composable
fun MyNavHost(navController: NavHostController, sharedViewModel: SharedViewModel) {

    NavHost(
        navController,
        startDestination = Routes.Home.route
    ){
        composable(Routes.Home.route){
            HomeScreen(navController, sharedViewModel)
        }
        composable(Routes.Chat.route){
            ChatScreen(sharedViewModel)
        }
        composable(Routes.History.route){
            HistoryScreen(sharedViewModel)
        }
        composable("${Routes.Info.route}/{inputString}"){ navBackStackEntry ->
            /* Extracting the id from the route */
            val inputData = navBackStackEntry.arguments?.getString("inputString")
            /* We check if it's not null */
            inputData?.let { inputString->
                InfoScreen(inputString, sharedViewModel, navController)
            }


        }
    }
}