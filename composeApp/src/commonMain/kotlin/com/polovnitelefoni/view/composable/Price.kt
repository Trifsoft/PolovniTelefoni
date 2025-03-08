package com.polovnitelefoni.view.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Price(
    formatedPrice: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$formatedPrice €",
        color = Color.Red,
        fontWeight = FontWeight.Black,
        fontSize = 18.sp,
        modifier = modifier
    )
}