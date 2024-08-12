package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.layout.Box
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
import androidx.navigation.NavHostController
import com.android.ai.aitutor.domain.entities.History
import com.android.ai.aitutor.presentation.ui.components.HistoryItemCard
import com.android.ai.aitutor.presentation.ui.navigation.Routes
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel

@Composable
fun HistoryScreen(sharedViewModel: SharedViewModel, navController: NavHostController) {

    val conversations by sharedViewModel.conversations.collectAsState()
    val latestConversations = conversations.reversed()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 0.dp)
    ) {
        if (conversations.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ){
                Text(
                    text = "No History"
                )
            }

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
                    sharedViewModel.openConversation(latestConversations[it].conversation)
                    navController.navigate(Routes.Chat.route)
                }
            }
        }

    }
}
