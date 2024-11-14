package com.raulbarca.portfolio.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.raulbarca.portfolio.core.localization.locale.Resources

/**
 * [PortfolioLoading] is a composable that displays a loading indicator.
 * @param modifier Modifier to be applied to the loading indicator.
 */
@Composable
fun PortfolioLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(Resources.strings.loading),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}