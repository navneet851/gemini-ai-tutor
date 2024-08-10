package com.android.ai.aitutor.presentation.ui.utilities

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import com.android.ai.aitutor.domain.entities.Subject
import org.json.JSONObject
import kotlin.math.abs

fun extractJson(text: String): String {
    val inputString = text.trimIndent()
    val jsonStartIndex = text.indexOf("{")
    val jsonEndIndex = text.lastIndexOf("}") + 1
    return if (jsonStartIndex != -1 && jsonEndIndex != -1) {
        text.substring(jsonStartIndex, jsonEndIndex).trim()
    } else {
        throw IllegalArgumentException("No JSON data found in the input string")
    }
}

fun jsonObjectToSubjectList(jsonObject: JSONObject): List<Subject> {
    val subjectsArray = jsonObject.getJSONArray("subjects")
    val subjectsList = mutableListOf<Subject>()

    for (i in 0 until subjectsArray.length()) {
        val subjectObject = subjectsArray.getJSONObject(i)
        val name = subjectObject.getString("name")
        val subtopicsArray = subjectObject.getJSONArray("subtopics")
        val subtopicsList = mutableListOf<String>()

        for (j in 0 until subtopicsArray.length()) {
            subtopicsList.add(subtopicsArray.getString(j))
        }

        subjectsList.add(Subject(name, subtopicsList))
    }

    return subjectsList
}

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    // this function is basically draw
    // a line to our second point and
    // also smooth on that line and make it curve
    quadraticTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f,
        abs(from.y + to.y) / 2f
    )
}