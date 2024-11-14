package com.raulbarca.portfolio.core.localization.strings

/**
 * Using this instead of native resources handling while WasmJS has no support for
 * change Locale.current (it gets browser language from javascript window.navigator.languages).
 *
 * https://youtrack.jetbrains.com/issue/CMP-4347
 *
 * https://youtrack.jetbrains.com/issue/CMP-4197/
 */
interface StringResources {
    val loading: String
    val error: String
    val somethingWentWrong: String
    val back: String
    val avatarImage: String
    fun changeUiMode(current: String): String
    val uiModeDark: String
    val uiModeLight: String
    fun changeLanguage(current: String): String
    val selected: String
    val contactMe: String
    val resume: String
    val whoIAm: String
    val whatIDo: String
    val projects: String
    val experience: String
    val education: String
    val selectLanguage: String
}