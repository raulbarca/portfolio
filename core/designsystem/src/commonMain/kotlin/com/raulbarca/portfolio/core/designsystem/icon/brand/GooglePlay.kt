package com.raulbarca.portfolio.core.designsystem.icon.brand

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.raulbarca.portfolio.core.designsystem.icon.AppIcons
import kotlin.Suppress

/**
 * https://fontawesome.com/icons/google-play?f=brands&s=solid
 */
val AppIcons.Brand.GooglePlay: ImageVector
    get() {
        if (_GooglePlay != null) {
            return _GooglePlay!!
        }
        _GooglePlay = ImageVector.Builder(
            name = "Brand.GooglePlay",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(325.3f, 234.3f)
                lineTo(104.6f, 13f)
                lineToRelative(280.8f, 161.2f)
                lineToRelative(-60.1f, 60.1f)
                close()
                moveTo(47f, 0f)
                curveTo(34f, 6.8f, 25.3f, 19.2f, 25.3f, 35.3f)
                verticalLineToRelative(441.3f)
                curveToRelative(0f, 16.1f, 8.7f, 28.5f, 21.7f, 35.3f)
                lineToRelative(256.6f, -256f)
                lineTo(47f, 0f)
                close()
                moveTo(472.2f, 225.6f)
                lineToRelative(-58.9f, -34.1f)
                lineToRelative(-65.7f, 64.5f)
                lineToRelative(65.7f, 64.5f)
                lineToRelative(60.1f, -34.1f)
                curveToRelative(18f, -14.3f, 18f, -46.5f, -1.2f, -60.8f)
                close()
                moveTo(104.6f, 499f)
                lineToRelative(280.8f, -161.2f)
                lineToRelative(-60.1f, -60.1f)
                lineTo(104.6f, 499f)
                close()
            }
        }.build()

        return _GooglePlay!!
    }

@Suppress("ObjectPropertyName")
private var _GooglePlay: ImageVector? = null
