package com.android.ai.aitutor.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationRailBar(navController : NavHostController) {

    val items = listOf(
        Routes.Home, Routes.Chat, Routes.History
    )
    val navStack by navController.currentBackStackEntryAsState()
    val currentRoute = navStack?.destination?.route

    NavigationRail(
        header = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .offset(x = (-1).dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Bottom)
        ) {
            items.forEach {
                NavigationRailItem(
                    selected = it.route == currentRoute,
                    onClick = {
                        if (it.route != currentRoute)
                            navController.navigate(it.route)
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
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
}