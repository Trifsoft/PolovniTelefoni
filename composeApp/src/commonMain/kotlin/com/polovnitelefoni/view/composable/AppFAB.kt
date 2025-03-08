package com.polovnitelefoni.view.composable

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppFAB(
    imageVector: ImageVector,
    onClick: ()->Unit
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}