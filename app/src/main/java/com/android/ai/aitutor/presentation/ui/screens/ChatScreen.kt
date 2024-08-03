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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ai.aitutor.R
import com.android.ai.aitutor.presentation.ui.components.AiResponse
import com.android.ai.aitutor.presentation.ui.components.UserResponse

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChatScreen() {
    Scaffold(

        bottomBar = {
            var text by remember { mutableStateOf("") }
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
                    onClick = { /*TODO*/ }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "send")
                }
            }
        }
    ) {

        val sampleConversations = listOf(
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
        val longTextConversations = listOf(
            Conversation(
                peer = "AI",
                chat = Chat(
                    text = "This is a very long text message from the AI peer. It contains a lot of information and details that are meant to simulate a real conversation. The purpose of this message is to test how the UI handles long text content and ensures that everything is displayed correctly without any issues.",
                    image = "ai_image_1.png"
                )
            ),
            Conversation(
                peer = "User",
                chat = Chat(
                    text = "This is another long text message, but this time from the user peer. The user is providing a detailed explanation or asking a complex question that requires a lot of text to convey properly. This helps in testing the application's ability to manage and display long user inputs effectively.",
                    image = "user_image_1.png"
                )
            ),
            Conversation(
                peer = "AI",
                chat = Chat(
                    text = "Here is yet another example of a long text message from the AI peer. This message is designed to be even longer than the previous ones, pushing the limits of the text handling capabilities of the application. It includes multiple sentences and a lot of content to ensure comprehensive testing.",
                    image = "ai_image_2.png"
                )
            ),
            Conversation(
                peer = "User",
                chat = Chat(
                    text = "The user is now providing a very detailed and lengthy response. This message includes several points and explanations, making it one of the longest messages in this list. The goal is to see how well the application can handle and display such extensive user input without any truncation or layout issues.",
                    image = "user_image_2.png"
                )
            )
        )

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
            items(longTextConversations.size){convo ->
                if (longTextConversations[convo].peer == "AI")
                    AiResponse(longTextConversations[convo].chat)
                else
                    UserResponse(longTextConversations[convo].chat)
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
    val image : String
)