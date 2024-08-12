package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.ai.aitutor.domain.entities.History
import com.android.ai.aitutor.presentation.ui.components.HistoryItemCard
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel

@Composable
fun HistoryScreen(sharedViewModel: SharedViewModel) {

    val conversations by sharedViewModel.conversations.collectAsState()
    val latestConversations = conversations.reversed()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 0.dp)
    ) {
        if (conversations.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),
                text = "No History"
            )
        } else {

            repeat(latestConversations.size) {
                HistoryItemCard(
                    history = History(
                        dateAndTime = latestConversations[it].time,
                        topic = latestConversations[it].title
                    ),
                    {
                        sharedViewModel.deleteConversation(latestConversations[it].id)
                    }
                ) {

                }
            }
        }

    }
}
