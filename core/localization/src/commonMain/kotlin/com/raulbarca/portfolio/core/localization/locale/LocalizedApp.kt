package com.raulbarca.portfolio.core.localization.locale

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale
import com.raulbarca.portfolio.core.localization.strings.StringResources
import com.raulbarca.portfolio.core.localization.strings.StringResourcesEn
import com.raulbarca.portfolio.core.localization.strings.StringResourcesFr
import com.raulbarca.portfolio.core.localization.strings.StringResourcesPt

val LocalLocalization = staticCompositionLocalOf { AppLocale.English.isoFormat }
val LocalStringResources = staticCompositionLocalOf<StringResources> { StringResourcesEn() }

var CurrentAppLocale: AppLocale =
    AppLocale.entries.firstOrNull { it.isoFormat in Locale.current.language } ?: AppLocale.English

object StringFactory {
    fun createStrings(appLocale: AppLocale): StringResources = when (appLocale) {
        AppLocale.English -> StringResourcesEn()
        AppLocale.French -> StringResourcesFr()
        AppLocale.Portuguese -> StringResourcesPt()
    }
}

object Resources {
    val strings: StringResources
        @Composable
        @ReadOnlyComposable
        get() = LocalStringResources.current
}

@Composable
fun LocalizedApp(
    appLocale: AppLocale = CurrentAppLocale,
    stringResources: StringResources = StringFactory.createStrings(appLocale),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalLocalization provides appLocale.isoFormat,
        LocalStringResources provides stringResources,
        content = content,
    )
}