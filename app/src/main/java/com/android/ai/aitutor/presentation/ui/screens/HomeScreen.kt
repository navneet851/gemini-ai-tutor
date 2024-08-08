package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ai.aitutor.R
import com.android.ai.aitutor.presentation.ui.components.ClassesCard


@Preview
@Composable
fun HomeScreen() {

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.home_poster),
                contentDescription = "poster",
                modifier = Modifier
                    .size(350.dp)
            )

            LazyHorizontalGrid(rows = GridCells.Fixed(2)) {
                items(10){
                    ClassesCard()
                }

            }
        }
}