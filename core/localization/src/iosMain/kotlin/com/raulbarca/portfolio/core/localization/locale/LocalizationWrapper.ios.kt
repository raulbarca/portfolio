package com.raulbarca.portfolio.core.localization.locale

import platform.Foundation.NSUserDefaults

actual fun changeAppLocale(appLocale: AppLocale) {
    CurrentAppLocale = appLocale
    NSUserDefaults.standardUserDefaults.setObject(arrayListOf(appLocale.isoFormat), "AppleLanguages")
}