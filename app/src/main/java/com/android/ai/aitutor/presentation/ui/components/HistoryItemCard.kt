package com.android.ai.aitutor.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.ai.aitutor.domain.entities.History


@Composable
fun HistoryItemCard(history : History, onDelete : () -> Unit, onClick : () -> Unit) {
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
        Column(modifier = Modifier.weight(1f)) {
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = history.topic
            )
            Text(
                text = history.dateAndTime,

                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                tint = Color.Gray,
                imageVector = Icons.Default.Delete,
                contentDescription = "delete"
            )
        }
    }
}


