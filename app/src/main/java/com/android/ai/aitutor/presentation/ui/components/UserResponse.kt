package com.android.ai.aitutor.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ai.aitutor.R
import com.android.ai.aitutor.presentation.ui.screens.Chat


@Composable
fun UserResponse(chat: Chat) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 5.dp)
            .padding(start = 50.dp)

    ) {

        Text(
            modifier = Modifier
                .weight(1f)

                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .padding(5.dp),
            text = chat.text
        )
        Image(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .size(25.dp)
                .padding(2.dp),
            painter = painterResource(id = R.drawable.male),
            contentDescription = "ai"
        )
    }
}