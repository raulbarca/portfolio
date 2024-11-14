package com.raulbarca.portfolio.core.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.raulbarca.portfolio.core.localization.locale.Resources

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = IconButton(
    onClick = onClick,
    modifier = modifier,
) {
    Icon(
        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
        contentDescription = Resources.strings.back,
    )
}