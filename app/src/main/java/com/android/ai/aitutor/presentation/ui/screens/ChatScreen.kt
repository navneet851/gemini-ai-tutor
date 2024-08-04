package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.ai.aitutor.R
import com.android.ai.aitutor.UiState
import com.android.ai.aitutor.presentation.ui.components.AiResponse
import com.android.ai.aitutor.presentation.ui.components.UserResponse
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChatScreen() {

    val sampleConversations : MutableList<Conversation> = rememberSaveable {
        mutableListOf(
            Conversation(
                peer = "AI",
                chat = Chat(
                    text = "Hello! How can I assist you today?",
                    image = "ai_image_1.png"
                )
            ),
            Conversation(
                peer = "User",
                chat = Chat(
                    text = "I need help with my homework.",
                    image = "user_image_1.png"
                )
            ),
            Conversation(
                peer = "AI",
                chat = Chat(
                    text = "Sure, what subject are you working on?",
                    image = "ai_image_2.png"
                )
            ),
            Conversation(
                peer = "User",
                chat = Chat(
                    text = "I'm working on a math problem.",
                    image = "user_image_2.png"
                )
            ),
            Conversation(
                peer = "AI",
                chat = Chat(
                    text = "Great! Let's solve it together.",
                    image = "ai_image_3.png"
                )
            )
        )
    }

    val chatViewModel : SharedViewModel = viewModel()
    val uiState by chatViewModel.uiState.collectAsState()
    var result by rememberSaveable { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Scaffold(

        bottomBar = {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(50),
                    value = text,
                    onValueChange = {
                        text = it
                    },
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
                        chatViewModel.sendPrompt(text)
                        sampleConversations.add(
                            Conversation(
                                peer = "User",
                                chat = Chat(text = text)
                            )

                        )
                    }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "send")
                }
            }
        }
    ) {

        if (uiState is UiState.Success) {
                result = (uiState as UiState.Success).outputText
                sampleConversations.add(
                    Conversation(
                        peer = "AI",
                        chat = Chat(text = result)
                    )
                )
            }



        LazyColumn(
            modifier = Modifier
                .padding(it)
        ) {
            items(sampleConversations.size){convo ->
                if (sampleConversations[convo].peer == "AI")
                    AiResponse(sampleConversations[convo].chat)
                else
                    UserResponse(sampleConversations[convo].chat)
            }

        }

    }
}

data class Conversation(
    val peer : String,
    val chat : Chat
)

data class Chat(
    val text : String,
    val image : String = ""
)