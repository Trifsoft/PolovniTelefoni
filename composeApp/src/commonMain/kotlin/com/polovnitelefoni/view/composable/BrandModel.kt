package com.polovnitelefoni.view.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun BrandModel(
    text: String,
    fontSize: TextUnit
) {
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        fontSize = fontSize,
        maxLines = 1,
        style = TextStyle.Default,
        fontWeight = FontWeight.SemiBold
    )
}