package com.android.ai.aitutor

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.ai.aitutor.presentation.ui.components.SliderItemCard
import com.android.ai.aitutor.presentation.ui.navigation.BottomBar
import com.android.ai.aitutor.presentation.ui.navigation.MyNavHost
import com.android.ai.aitutor.presentation.ui.navigation.NavigationRailBar
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(mainActivity: MainActivity) {

    val sharedViewModel : SharedViewModel = viewModel()
    val navController = rememberNavController()
    val windowClass = calculateWindowSizeClass(activity = mainActivity)
    val showNavigationRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .padding(10.dp),
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }

                        }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                    }

                    Text(
                        text = "All Chats",
                        fontSize = 18.sp
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    items(10){
                        SliderItemCard("gfggdth hfghdfdvdsvfvsdfvfdsdvffgfdgrgrgsrvdh hdfhdf", {}){

                        }
                    }
                }

            }
        },
        drawerState = drawerState
    ) {

        Scaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                CenterAlignedTopAppBar(
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }

                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    title = {
                        Text(
                            fontSize = 18.sp,
                            text = when(currentRoute){
                                "home" -> "Home"
                                "chat" -> "Chat"
                                "history" -> "History"
                                else -> ""
                            }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        //containerColor = Color(0x1D651FFF),
                    )
                )
            },
            bottomBar = {
                if (!WindowInsets.isImeVisible && !showNavigationRail) {
                    BottomBar(navController)
                }

            }
        ) {

            Box(
                modifier = Modifier
                    .padding(it)
                    .padding(start = if (showNavigationRail) 80.dp else 0.dp,)
            ) {
                MyNavHost(navController, sharedViewModel)
            }

        }


        if (showNavigationRail) {
            NavigationRailBar(navController = navController){
                coroutineScope.launch {
                    drawerState.open()
                }
            }
        }
    }
}


