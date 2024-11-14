package com.raulbarca.portfolio.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.raulbarca.portfolio.core.localization.locale.Resources

/**
 * Displays an error message.
 * @param modifier Modifier to be applied to the error message.
 * @param text Text to be displayed.
 */
@Composable
fun PortfolioError(
    modifier: Modifier = Modifier,
    text: String = Resources.strings.somethingWentWrong,
) {
    Box(
        modifier = modifier
            .testTag(Resources.strings.error),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}