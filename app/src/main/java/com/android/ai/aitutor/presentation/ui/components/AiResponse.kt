package com.android.ai.aitutor.presentation.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.android.ai.aitutor.R
import com.android.ai.aitutor.domain.entities.Chat


@Composable
fun AiResponse(chat: Chat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 2.dp)
            .padding(end = 60.dp),

        ) {
        val clipboardManager: ClipboardManager = LocalClipboardManager.current
        val context = LocalContext.current

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .size(20.dp)
                    .padding(3.dp),
                painter = painterResource(id = R.drawable.gemini_ai_icon),
                contentDescription = "ai"
            )
            Icon(
                modifier = Modifier
                    .padding(0.dp, 4.dp)
                    .size(16.dp)
                    .clickable {
                        clipboardManager.setText(AnnotatedString(chat.text))
                        Toast
                            .makeText(context, "Copied", Toast.LENGTH_SHORT)
                            .show()
                    },
                tint = Color.Gray,
                painter = painterResource(id = R.drawable.baseline_content_copy_24),
                contentDescription = "copy"
            )
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0x4A6EA1FA))
                .padding(5.dp)
                .clickable {

                },
            text = chat.text
        )
    }
}