package com.raulbarca.portfolio.core.localization.locale

import java.util.Locale

actual fun changeAppLocale(appLocale: AppLocale) {
    CurrentAppLocale = appLocale
    val locale = Locale(appLocale.isoFormat)
    Locale.setDefault(locale)
}