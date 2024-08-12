package com.android.ai.aitutor.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.android.ai.aitutor.domain.entities.User


@Composable
fun UserDialog(onClick : (User) -> Unit) {

    data class ToggleableInfo(
        val isChecked: Boolean,
        val gender: String
    )

    var text by remember {
        mutableStateOf("")
    }

    var radioButtons by remember {
        mutableStateOf("Male")
    }

    Box(
        contentAlignment = Alignment.Center,
        //modifier = Modifier.fillMaxSize()
    ) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(14.dp)
            ) {
                Text(text = "Name")
                Spacer(modifier = Modifier.padding(3.dp))
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Gender")
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                radioButtons = "Male"
                            }
                            .padding(end = 16.dp)
                    ) {
                        RadioButton(
                            selected = radioButtons == "Male",
                            onClick = {
                                radioButtons = "Male"
                            }
                        )
                        Text(text = "Male")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                radioButtons = "Female"
                            }
                            .padding(end = 16.dp)
                    ) {
                        RadioButton(
                            selected = radioButtons == "Female",
                            onClick = {
                                radioButtons = "Female"
                            }
                        )
                        Text(text = "Female")
                    }

                }
                
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        onClick(User(1, text, radioButtons))
                    }
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}