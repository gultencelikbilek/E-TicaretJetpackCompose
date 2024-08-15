package com.example.e_ticaret.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun Header(text : String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = Color.Black
        )
    )
}