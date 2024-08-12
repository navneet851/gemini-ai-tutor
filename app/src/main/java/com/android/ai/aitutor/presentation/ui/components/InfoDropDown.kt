package com.android.ai.aitutor.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ai.aitutor.domain.entities.Subject

@Composable
fun InfoDropDown(subject: Subject, onItemSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(10.dp, 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .clickable { expanded = true }
        ) {
            Text(
                text = subject.name,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "open subject topics"
                )
            }
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                DropdownMenu(
                    scrollState = rememberScrollState(),
//                    containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    IconButton(onClick = { expanded = false }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "open subject topics"
                        )
                    }

                    subject.subtopics.forEach { subtopic ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .padding(5.dp, 2.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.inverseOnSurface),
                            text = { Text(subtopic) },
                            onClick = {
                                expanded = false
                                onItemSelected("$subtopic ${subject.name}" )
                            }
                        )
                    }
                }
            }
        }
    }


}

@Preview
@Composable
fun InfoDropDownPreview() {
    val subjects = listOf(
        Subject("Mathematics", listOf("Arithmetic", "Algebra", "Geometry")),
        Subject("Science", listOf("Physics", "Chemistry", "Biology"))
    )
    InfoDropDown(
        Subject(
            "Mathematics",
            listOf("Arithmetic", "Algebra", "Geometry")
        )
    ) { selectedItem ->
        // Handle item selection
    }
}