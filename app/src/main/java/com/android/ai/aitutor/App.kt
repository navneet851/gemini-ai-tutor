package com.android.ai.aitutor

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.ai.aitutor.presentation.ui.navigation.BottomBar
import com.android.ai.aitutor.presentation.ui.navigation.MyNavHost
import com.android.ai.aitutor.presentation.ui.navigation.NavigationRailBar
import com.android.ai.aitutor.presentation.ui.navigation.Routes

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(mainActivity: MainActivity) {

    val navController = rememberNavController()
    val windowClass = calculateWindowSizeClass(activity = mainActivity)
    val showNavigationRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                title = {
                    Text(text = currentRoute ?: "")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    //containerColor = Color(0x1D651FFF),
                )
            )
        },
        bottomBar = {
            if (!showNavigationRail)
                BottomBar(navController)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .padding( start = if (showNavigationRail) 80.dp else 0.dp,)
        ){
            MyNavHost(navController)
        }
    }

    if (showNavigationRail){
        NavigationRailBar(navController = navController)
    }
}