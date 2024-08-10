package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.ai.aitutor.R
import com.android.ai.aitutor.UiState
import com.android.ai.aitutor.domain.entities.Chat
import com.android.ai.aitutor.presentation.ui.components.AiResponse
import com.android.ai.aitutor.presentation.ui.components.UserResponse
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel

@Composable
fun ChatScreen(chatViewModel: SharedViewModel) {

    val currentConversation by chatViewModel.currentConversation.collectAsState()
    val uiState by chatViewModel.uiState.collectAsState()
    var result by rememberSaveable { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    var listScrollState by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(listScrollState, uiState) {
        listState.animateScrollToItem(currentConversation.size - 1)
    }

    Scaffold(

        bottomBar = {

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .weight(1f)
                    ,
                    shape = if (text.lines().size > 1) RoundedCornerShape(10.dp) else RoundedCornerShape(50.dp),
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    maxLines = 5,
                    trailingIcon = {
                        Row {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(painter = painterResource(id = R.drawable.outline_photo_camera_24), contentDescription = "camera")
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(painter = painterResource(id = R.drawable.baseline_photo_library_24), contentDescription = "send")
                            }
                        }

                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    )
                )

                IconButton(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFF8B64F8))
                        ,
                    onClick = {
                        if (text != ""){
                            chatViewModel.sendConversationPrompt(text)
                            text = ""
                            listScrollState = !listScrollState
                        }
                    }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "send")
                }
            }
        }
    ) {

        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(bottom = it.calculateBottomPadding())
                .fillMaxSize()
        ) {
            items(currentConversation.size){ convo ->
                if (currentConversation[convo].peer == "AI")
                    AiResponse(currentConversation[convo].chat)

                else
                    UserResponse(currentConversation[convo].chat)
            }

            if (uiState is UiState.Loading){
                item {
                    AiResponse(chat = Chat(text = "typing..."))
                }
            }
            if (uiState is UiState.Error){
                item {
                    AiResponse(chat = Chat(text = "try again!!"))
                }
            }


        }

    }
}

