package com.android.ai.aitutor.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.ai.aitutor.R
import com.android.ai.aitutor.UiState
import com.android.ai.aitutor.domain.entities.Banner
import com.android.ai.aitutor.presentation.ui.components.BannerTemplate
import com.android.ai.aitutor.presentation.ui.components.InfoDropDown
import com.android.ai.aitutor.presentation.ui.components.Loader
import com.android.ai.aitutor.presentation.ui.navigation.Routes
import com.android.ai.aitutor.presentation.ui.utilities.extractJson
import com.android.ai.aitutor.presentation.ui.utilities.jsonObjectToSubjectList
import com.android.ai.aitutor.presentation.viewmodel.SharedViewModel
import org.json.JSONObject

@Composable
fun InfoScreen(
    inputString: String,
    infoViewModel: SharedViewModel,
    navController: NavHostController
) {



    val inputPrompt = if(inputString.contains("Subject")){
        """Provide me $inputString subject topic list which contain subject topics and there subtopics as this example json format
        {
     "subjects": [
    {
      "name": "Calculus",
      "subtopics": [
        "Limits",
        "Derivatives",
        "Integrals"
      ]
    },
    {
      "name": "Vectors",
      "subtopics": [
        "Dot Product",
        "Cross Product",
        "Geometry"
      ]
      ]
    },
  ]
}
    """.trimMargin()
    }
    else{
        """Provide me class $inputString school subjects list as this json format
        {
  "subjects": [
    {
      "name": "Mathematics",
      "subtopics": [
        "Arithmetic",
        "Algebra",
        "Geometry",
        "Trigonometry",
        "Statistics"
      ]
    },
    {
      "name": "Science",
      "subtopics": [
        "Physics",
        "Chemistry",
        "Biology"
      ]
    },
  ]
}
    """.trimMargin()
    }
    val uiState by infoViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        infoViewModel.sendPrompt(inputPrompt)
    }

    if (uiState is UiState.Loading) {
        Loader()
    }

    if (uiState is UiState.Success) {
        val jsonData = extractJson((uiState as UiState.Success).outputText.trimIndent())
        val jsonObject = JSONObject(jsonData)
        val subjectsList = jsonObjectToSubjectList(jsonObject)


        LazyColumn(
            modifier = Modifier
                .padding(16.dp, 0.dp)
                .fillMaxSize()
        ) {
            item {
                BannerTemplate(
                    bannerDetail =
                    Banner(
                        title = inputString,
                        R.drawable.education,
                        Color(0x6D7B77FD),
                        Color(0x727B77FD),
                        Color(0x5B7B77FD)
                    )
                ) {
                    navController.navigate(Routes.Chat.route)
                }
            }
            items(subjectsList.size) {
                InfoDropDown(subject = subjectsList[it]) {

                }
            }
        }
    }


}