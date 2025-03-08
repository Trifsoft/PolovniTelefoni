package com.polovnitelefoni.icons


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val Icons.Filled.Battery1Bar: ImageVector
    get() {
        if (_battery1Bar != null) {
            return _battery1Bar!!
        }
        _battery1Bar = materialIcon(name = "Filled.Battery1Bar") {
            materialPath {
                moveTo(17.0f, 5.0f)
                verticalLineToRelative(16.0f)
                curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f)
                horizontalLineTo(8.0f)
                curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f)
                verticalLineTo(5.0f)
                curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f)
                horizontalLineToRelative(2.0f)
                verticalLineTo(2.0f)
                horizontalLineToRelative(4.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(2.0f)
                curveTo(16.55f, 4.0f, 17.0f, 4.45f, 17.0f, 5.0f)
                close()
                moveTo(15.0f, 6.0f)
                horizontalLineTo(9.0f)
                verticalLineToRelative(12.0f)
                horizontalLineToRelative(6.0f)
                verticalLineTo(6.0f)
                close()
            }
        }
        return _battery1Bar!!
    }

private var _battery1Bar: ImageVector? = null