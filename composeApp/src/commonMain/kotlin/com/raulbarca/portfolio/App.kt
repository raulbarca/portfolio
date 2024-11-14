package com.raulbarca.portfolio

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.raulbarca.portfolio.core.designsystem.theme.AppThemeUiMode
import com.raulbarca.portfolio.core.designsystem.theme.PortfolioTheme
import com.raulbarca.portfolio.core.localization.locale.CurrentAppLocale
import com.raulbarca.portfolio.core.localization.locale.LocalizedApp
import com.raulbarca.portfolio.core.localization.locale.changeAppLocale
import com.raulbarca.portfolio.navigation.PortfolioNavGraph
import okio.FileSystem
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App(
    disableDiskCache: Boolean = false,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    var appThemeUiMode by rememberSaveable {
        mutableStateOf(if (isSystemInDarkTheme) AppThemeUiMode.Dark else AppThemeUiMode.Light)
    }
    var appLocale by rememberSaveable { mutableStateOf(CurrentAppLocale) }

    LocalizedApp(appLocale = appLocale) {
        PortfolioTheme(isSystemInDarkTheme = appThemeUiMode == AppThemeUiMode.Dark) {
            KoinContext {
                setSingletonImageLoaderFactory { context ->
                    if (disableDiskCache) context.asyncImageLoader() else
                        context.asyncImageLoader().enableDiskCache()
                }
                PortfolioNavGraph(
                    onUiModeChange = { appThemeUiMode = it },
                    onAppLocaleChange = {
                        changeAppLocale(it)
                        appLocale = it
                    }
                )
            }
        }
    }
}

/**
 * Create a new [ImageLoader] with the [PlatformContext].
 */
fun PlatformContext.asyncImageLoader() =
    ImageLoader
        .Builder(this)
        .components { add(KtorNetworkFetcherFactory()) }
        .crossfade(true)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(this, 0.25)
                .strongReferencesEnabled(true)
                .build()
        }
        .logger(DebugLogger())
        .build()

/**
 * Enable disk cache for the [ImageLoader].
 */
fun ImageLoader.enableDiskCache() = this.newBuilder()
    .diskCache {
        DiskCache.Builder()
            .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
            .build()
    }.build()