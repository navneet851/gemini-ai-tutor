package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.android.ai.aitutor.UiState
import com.android.ai.aitutor.presentation.ui.utilities.extractJson
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel
import org.json.JSONObject

@Composable
fun InfoScreen(inputString: String, infoViewModel: SharedViewModel) {

    val inputPrompt = "Provide me 12th class subjects list in json format in this format {\n" +
            "  \"subjects\": [\n" +
            "    {\n" +
            "      \"name\": \"Mathematics\",\n" +
            "      \"subtopics\": [\n" +
            "        \"Arithmetic\",\n" +
            "        \"Algebra\",\n" +
            "        \"Geometry\",\n" +
            "        \"Trigonometry\",\n" +
            "        \"Statistics\",\n" +
            "        // ... more subtopics\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Science\",\n" +
            "      \"subtopics\": [\n" +
            "        \"Physics\",\n" +
            "        \"Chemistry\",\n" +
            "        \"Biology\"\n" +
            "      ]\n" +
            "    },\n" +
            "    // ... other subjects\n" +
            "  ]\n" +
            "}"
    val uiState by infoViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        infoViewModel.sendPrompt(inputPrompt)
    }

    if (uiState is UiState.Loading){
        Text(text = "loading")
    }

    if (uiState is UiState.Success){
        val jsonData = extractJson((uiState as UiState.Success).outputText.trimIndent())
        val jsonObject = JSONObject(jsonData)
        Text(modifier = Modifier.verticalScroll(rememberScrollState()), text = jsonObject.toString())
    }
    

    

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(10.dp)
//    ) {
//        repeat(5){
//            HistoryItemCard(history = History("12 3, 5 5", "gemini")){
//
//            }
//        }
//    }
}