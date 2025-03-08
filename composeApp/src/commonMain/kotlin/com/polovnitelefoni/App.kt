package com.polovnitelefoni

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import com.polovnitelefoni.values.Colors
import com.polovnitelefoni.view.MainScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = lightColors(
            primary = Color(0xff50c3ff),
            primaryVariant = Color(0xFF0088CC),
            secondary = Color(0xFFFF8E50),
            secondaryVariant = Color(0xFFC4622B),
            onSecondary = Color.White,
            background = Color(0xFFF5F5F5)
        )
    ) {
        Navigator(MainScreen)
    }
}