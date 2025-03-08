package com.polovnitelefoni.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val Icons.AutoMirrored.Filled.ArrowBackIos: ImageVector
    get() {
        if (_arrowBackIos != null) {
            return _arrowBackIos!!
        }
        _arrowBackIos = materialIcon(name = "AutoMirrored.Filled.ArrowBackIos", autoMirror = true) {
            materialPath {
                moveTo(11.67f, 3.87f)
                lineTo(9.9f, 2.1f)
                lineTo(0.0f, 12.0f)
                lineToRelative(9.9f, 9.9f)
                lineToRelative(1.77f, -1.77f)
                lineTo(3.54f, 12.0f)
                close()
            }
        }
        return _arrowBackIos!!
    }

private var _arrowBackIos: ImageVector? = null


