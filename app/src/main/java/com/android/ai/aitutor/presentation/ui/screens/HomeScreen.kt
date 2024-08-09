package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ai.aitutor.R
import com.android.ai.aitutor.domain.entities.Banner
import com.android.ai.aitutor.presentation.ui.components.BannerTemplate


@Preview
@Composable
fun HomeScreen() {

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.home_poster),
                contentDescription = "poster",
                modifier = Modifier
                    .size(350.dp)
            )

            BannerTemplate(
                bannerDetail = Banner(
                    title = "Learn Faster and Efficiently with AI.",
                    R.drawable.male,
                    Color(0xFF9AF8BA),
                    Color(0xFFBAFCCA),
                    Color(0xFF75FCA3)
                )
            )
            BannerTemplate(
                bannerDetail = Banner(
                    title = "Chat With Our AI Assistant!",
                    R.drawable.chat_fill,
                    Color(0x4A6EA1FA),
                    Color(0x4A6EA1FA),
                    Color(0x4A6EA1FA)
                )
            )

//            LazyHorizontalGrid(rows = GridCells.Fixed(2)) {
//                items(10){
//                    ClassesCard()
//                }
//
//            }
        }
}