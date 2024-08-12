package com.android.ai.aitutor.presentation.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar(navController : NavHostController) {
    val items = listOf(
        Routes.Home, Routes.Chat, Routes.History
    )

    val navStack by navController.currentBackStackEntryAsState()
    val currentRoute = navStack?.destination?.route

    NavigationBar {

        items.forEach {
            NavigationBarItem(
                selected = it.route == currentRoute,
                onClick = {
                    if (it.route != currentRoute){
                        navController.navigate(it.route){
                            launchSingleTop = true
                            restoreState = true
                        }
                    }

                },
                icon = {
                    Image(
                        modifier = Modifier
                            .size(22.dp),
                        painter = if (it.route == currentRoute){
                            painterResource(id = it.selectedIcon)
                        }else{
                            painterResource(id = it.unSelectedIcon)
                            },
                        contentDescription = it.route
                    )
                },
                label = {
                    Text(text = it.label)
                }
            )

        }

    }
}