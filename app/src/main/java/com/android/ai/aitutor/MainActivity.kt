package com.android.ai.aitutor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.ai.aitutor.ui.theme.AITutorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AITutorTheme {
                // A surface container using the 'background' color from the theme
                App(this)
            }
        }
    }
}