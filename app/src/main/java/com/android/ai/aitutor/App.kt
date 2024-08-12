package com.android.ai.aitutor

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.ai.aitutor.presentation.ui.navigation.BottomBar
import com.android.ai.aitutor.presentation.ui.navigation.MyNavHost
import com.android.ai.aitutor.presentation.ui.navigation.NavigationRailBar
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(mainActivity: MainActivity) {

    val sharedViewModel: SharedViewModel = hiltViewModel()
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

                val gender = "male"

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
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }

                    Text(
                        text = "Profile",
                        fontSize = 18.sp
                    )
                }


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .width(280.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Image(
                        modifier = Modifier
                            .size(130.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.inverseOnSurface),
                        painter = painterResource(
                            id = if (gender == "male") {
                                R.drawable.male
                            } else {
                                R.drawable.female
                            }
                        ),
                        contentDescription = "user"
                    )

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Create, contentDescription = "edit")
                    }

                    Spacer(modifier = Modifier.padding(5.dp))

                    Column(
                        modifier = Modifier
                            .width(140.dp)
                            .height(130.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.inverseOnSurface),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {


                        Text(
                            fontSize = 18.sp,
                            text = "user name",
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        OutlinedCard {
                            Text(
                                modifier = Modifier
                                    .padding(30.dp, 5.dp),
                                text = "Male",
                                fontWeight = FontWeight.Medium
                            )
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
                            text = when (currentRoute) {
                                "home" -> "Home"
                                "chat" -> "Chat"
                                "history" -> "History"
                                else -> ""
                            }
                        )
                    },
                    actions = {
                       if(currentRoute == "chat"){
                           IconButton(onClick = {
                               sharedViewModel.addConversation()
                           }) {
                               Icon(imageVector = Icons.Default.Add, contentDescription = "create")
                           }
                       }

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
                    .fillMaxSize()
                    .padding(it)
                    .padding(start = if (showNavigationRail) 80.dp else 0.dp)
            ) {
                MyNavHost(navController, sharedViewModel)
            }

        }


        if (showNavigationRail) {
            NavigationRailBar(navController = navController) {
                coroutineScope.launch {
                    drawerState.open()
                }
            }
        }
    }
}


