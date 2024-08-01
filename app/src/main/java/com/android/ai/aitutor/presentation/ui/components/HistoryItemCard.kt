package com.android.ai.aitutor.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.ai.aitutor.R



@Composable
fun HistoryItemCard(history : History, onClick : () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp, 5.dp)
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(10.dp, 8.dp)
            .clickable {
                onClick()
            }
    ) {
        Column {
            Text(
                text = history.topic,
                fontSize = 16.sp
            )
            Text(
                text = history.dateAndTime,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        Image(
            painter = painterResource(id = R.drawable.history),
            contentDescription = "success order",
            modifier = Modifier.size(25.dp)
        )
    }
}

data class History(
    val dateAndTime : String,
    val topic : String
) {
    constructor() : this("", "")
}
