package com.raulbarca.portfolio.feature.overview.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selectableGroup
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.raulbarca.portfolio.core.localization.locale.AppLocale
import com.raulbarca.portfolio.core.localization.locale.CurrentAppLocale
import com.raulbarca.portfolio.core.localization.locale.Resources

/**
 * Dialog for choosing a language from [AppLocale] entries.
 *
 * @param onAppLocaleChosen called when the user chooses an [AppLocale] option.
 * @param onDismissRequest called when the user tries to dismiss the Dialog by clicking outside or
 *   pressing the back button.
 * @param modifier the [Modifier] to be applied to this dialog's content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LanguageChooserDialog(
    onAppLocaleChosen: (AppLocale) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) = BasicAlertDialog(onDismissRequest = onDismissRequest, modifier = modifier) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = Resources.strings.selectLanguage,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.semantics {
                    heading()
                }
            )
            Spacer(Modifier.size(8.dp))
            AppLocale.entries.forEach { appLocale ->
                TextButton(
                    onClick = { onAppLocaleChosen(appLocale) },
                    modifier = Modifier.semantics(mergeDescendants = true) {
                        selectableGroup()
                        selected = appLocale == CurrentAppLocale
                    }
                ) {
                    Text(
                        "${appLocale.text} (${appLocale.isoFormat})",
                        modifier = Modifier.weight(1f)
                    )
                    if (appLocale == CurrentAppLocale) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}