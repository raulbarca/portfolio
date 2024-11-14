package com.raulbarca.portfolio.core.designsystem.icon.brand

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.raulbarca.portfolio.core.designsystem.icon.AppIcons

/**
 * https://fontawesome.com/icons/linkedin?f=brands&s=solid
 */
val AppIcons.Brand.LinkedIn: ImageVector
    get() {
        if (_LinkedIn != null) {
            return _LinkedIn!!
        }
        _LinkedIn = ImageVector.Builder(
            name = "LinkedIn",
            defaultWidth = 448.dp,
            defaultHeight = 512.dp,
            viewportWidth = 448f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(416f, 32f)
                lineTo(31.9f, 32f)
                curveTo(14.3f, 32f, 0f, 46.5f, 0f, 64.3f)
                verticalLineToRelative(383.4f)
                curveTo(0f, 465.5f, 14.3f, 480f, 31.9f, 480f)
                lineTo(416f, 480f)
                curveToRelative(17.6f, 0f, 32f, -14.5f, 32f, -32.3f)
                lineTo(448f, 64.3f)
                curveToRelative(0f, -17.8f, -14.4f, -32.3f, -32f, -32.3f)
                close()
                moveTo(135.4f, 416f)
                lineTo(69f, 416f)
                lineTo(69f, 202.2f)
                horizontalLineToRelative(66.5f)
                lineTo(135.5f, 416f)
                close()
                moveTo(102.2f, 173f)
                curveToRelative(-21.3f, 0f, -38.5f, -17.3f, -38.5f, -38.5f)
                reflectiveCurveTo(80.9f, 96f, 102.2f, 96f)
                curveToRelative(21.2f, 0f, 38.5f, 17.3f, 38.5f, 38.5f)
                curveToRelative(0f, 21.3f, -17.2f, 38.5f, -38.5f, 38.5f)
                close()
                moveTo(384.3f, 416f)
                horizontalLineToRelative(-66.4f)
                lineTo(317.9f, 312f)
                curveToRelative(0f, -24.8f, -0.5f, -56.7f, -34.5f, -56.7f)
                curveToRelative(-34.6f, 0f, -39.9f, 27f, -39.9f, 54.9f)
                lineTo(243.5f, 416f)
                horizontalLineToRelative(-66.4f)
                lineTo(177.1f, 202.2f)
                horizontalLineToRelative(63.7f)
                verticalLineToRelative(29.2f)
                horizontalLineToRelative(0.9f)
                curveToRelative(8.9f, -16.8f, 30.6f, -34.5f, 62.9f, -34.5f)
                curveToRelative(67.2f, 0f, 79.7f, 44.3f, 79.7f, 101.9f)
                lineTo(384.3f, 416f)
                close()
            }
        }.build()

        return _LinkedIn!!
    }

@Suppress("ObjectPropertyName")
private var _LinkedIn: ImageVector? = null
