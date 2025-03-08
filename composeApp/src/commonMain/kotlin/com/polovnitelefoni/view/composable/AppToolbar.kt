package com.polovnitelefoni.view.composable

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.polovnitelefoni.expect.Platform
import com.polovnitelefoni.expect.getPlatform
import com.polovnitelefoni.icons.ArrowBackIos

@Composable
fun AppToolbar(
    title: String
) {
    val navigator = LocalNavigator.currentOrThrow
    val backIconVector = if(getPlatform() == Platform.ANDROID) {
        Icons.AutoMirrored.Default.ArrowBack
    } else Icons.AutoMirrored.Default.ArrowBackIos
    TopAppBar(
        title = { Text(title)},
        navigationIcon = {
            IconButton(
                onClick = { navigator.pop()}
            ) {
                Icon(
                    imageVector = backIconVector,
                    null,
                )
            }
        }
    )
}