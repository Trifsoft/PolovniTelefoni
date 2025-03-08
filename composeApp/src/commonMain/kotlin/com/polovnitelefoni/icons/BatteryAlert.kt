package com.polovnitelefoni.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val Icons.Filled.BatteryAlert: ImageVector
    get() {
        if (_batteryAlert != null) {
            return _batteryAlert!!
        }
        _batteryAlert = materialIcon(name = "Filled.BatteryAlert") {
            materialPath {
                moveTo(15.67f, 4.0f)
                lineTo(14.0f, 4.0f)
                lineTo(14.0f, 2.0f)
                horizontalLineToRelative(-4.0f)
                verticalLineToRelative(2.0f)
                lineTo(8.33f, 4.0f)
                curveTo(7.6f, 4.0f, 7.0f, 4.6f, 7.0f, 5.33f)
                verticalLineToRelative(15.33f)
                curveTo(7.0f, 21.4f, 7.6f, 22.0f, 8.33f, 22.0f)
                horizontalLineToRelative(7.33f)
                curveToRelative(0.74f, 0.0f, 1.34f, -0.6f, 1.34f, -1.33f)
                lineTo(17.0f, 5.33f)
                curveTo(17.0f, 4.6f, 16.4f, 4.0f, 15.67f, 4.0f)
                close()
                moveTo(13.0f, 18.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(2.0f)
                close()
                moveTo(13.0f, 14.0f)
                horizontalLineToRelative(-2.0f)
                lineTo(11.0f, 9.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(5.0f)
                close()
            }
        }
        return _batteryAlert!!
    }

private var _batteryAlert: ImageVector? = null
