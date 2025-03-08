package com.polovnitelefoni.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val Icons.Filled.SdStorage: ImageVector
    get() {
        if (_sdStorage != null) {
            return _sdStorage!!
        }
        _sdStorage = materialIcon(name = "Filled.SdStorage") {
            materialPath {
                moveTo(18.0f, 2.0f)
                horizontalLineToRelative(-8.0f)
                lineTo(4.02f, 8.0f)
                lineTo(4.0f, 20.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(12.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                lineTo(20.0f, 4.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                close()
                moveTo(12.0f, 8.0f)
                horizontalLineToRelative(-2.0f)
                lineTo(10.0f, 4.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(4.0f)
                close()
                moveTo(15.0f, 8.0f)
                horizontalLineToRelative(-2.0f)
                lineTo(13.0f, 4.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(4.0f)
                close()
                moveTo(18.0f, 8.0f)
                horizontalLineToRelative(-2.0f)
                lineTo(16.0f, 4.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(4.0f)
                close()
            }
        }
        return _sdStorage!!
    }

private var _sdStorage: ImageVector? = null