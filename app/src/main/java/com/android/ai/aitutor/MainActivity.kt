package com.android.ai.aitutor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.android.ai.aitutor.ui.theme.AITutorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AITutorTheme {
                // A surface container using the 'background' color from the theme

                App(this)
            }
        }
    }
}
