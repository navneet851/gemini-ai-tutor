package com.android.ai.aitutor

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.android.ai.aitutor.presentation.ui.navigation.BottomBar
import com.android.ai.aitutor.presentation.ui.navigation.MyNavHost
import com.android.ai.aitutor.presentation.ui.navigation.NavigationRailBar

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(mainActivity: MainActivity) {

    val navController = rememberNavController()
    val windowClass = calculateWindowSizeClass(activity = mainActivity)
    val showNavigationRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact


    Scaffold(
        bottomBar = {
            if (!showNavigationRail)
                BottomBar(navController)
        }
    ) {
        Box(
            modifier = Modifier
                .padding( start = if (showNavigationRail) 80.dp else 0.dp,)
        ){
            MyNavHost(navController)
        }
    }

    if (showNavigationRail){
        NavigationRailBar(navController = navController)
    }
}