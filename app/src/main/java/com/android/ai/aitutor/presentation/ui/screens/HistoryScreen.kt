package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ai.aitutor.presentation.ui.components.History
import com.android.ai.aitutor.presentation.ui.components.HistoryItemCard

@Preview
@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        repeat(5){
            HistoryItemCard(history = History("12 3, 5 5", "gemini")){

            }
        }
    }
}