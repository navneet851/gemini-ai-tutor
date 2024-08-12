package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.ai.aitutor.R
import com.android.ai.aitutor.domain.entities.Banner
import com.android.ai.aitutor.presentation.ui.components.BannerCard
import com.android.ai.aitutor.presentation.ui.components.BannerTemplate
import com.android.ai.aitutor.presentation.ui.navigation.Routes
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel


@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val user = sharedViewModel.user

    val classes = listOf(
        "1st",
        "2nd",
        "3rd",
        "4th",
        "5th",
        "6th",
        "7th",
        "8th",
        "9th",
        "10th",
        "11th",
        "12th"
    )
    val subjects = listOf(
        Banner(
            title = "Physics",
            R.drawable.atom,
            Color(0x6850F0BD),
            Color(0x6850F0BD),
            Color(0x6850F0BD)
        ),
        Banner(
            title = "Chemistry",
            R.drawable.laboratory,
            Color(0x70E971FD),
            Color(0x70E971FD),
            Color(0x70E971FD)
        ),
        Banner(
            title = "Math",
            R.drawable.math,
            Color(0x70FC1D5C),
            Color(0x70FC1D5C),
            Color(0x70FC1D5C)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.home_poster),
            contentDescription = "poster",
            modifier = Modifier
                .size(350.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            text = "Welcome ${user?.name ?: "User"}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        BannerTemplate(
            bannerDetail = Banner(
                title = "Learn Faster and Efficiently with AI.",
                R.drawable.male,
                Color(0x72CB89FA),
                Color(0x72D398FD),
                Color(0x72BC93DA)
            )
        ) {

        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            text = "Classes",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
        ) {
            items(classes.size) {
                BannerCard(
                    bannerDetail = Banner(
                        title = classes[it],
                        R.drawable.education,
                        Color(0x727B77FD),
                        Color(0x727B77FD),
                        Color(0x727B77FD)
                    )
                ){
                    navController.navigate("${Routes.Info.route}/${classes[it]} Class")
                }
            }

        }

        BannerTemplate(
            bannerDetail = Banner(
                title = "Chat With Our AI Assistant!",
                R.drawable.chat_fill,
                Color(0x4A6EA1FA),
                Color(0x4A6EA1FA),
                Color(0x4A6EA1FA)
            )
        ) {
            navController.navigate(Routes.Chat.route)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            text = "Subjects",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
        ) {
            items(subjects.size) {
                BannerCard(bannerDetail = subjects[it]){
                    navController.navigate("${Routes.Info.route}/${subjects[it].title} Subject")
                }
            }

        }


    }
}