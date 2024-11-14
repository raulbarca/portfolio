package com.raulbarca.portfolio

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.raulbarca.portfolio.core.designsystem.theme.backgroundDark
import com.raulbarca.portfolio.core.designsystem.theme.backgroundLight
import com.raulbarca.portfolio.di.appModule
import io.github.vinceglb.filekit.core.FileKit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                appModule,
                module {
                    single<Activity> { this@MainActivity }
                }
            )
        }

        FileKit.init(this)
        setContent {
            val view = LocalView.current
            if (!view.isInEditMode) {
                val isSystemInDarkTheme = isSystemInDarkTheme()
                val backgroundColor =
                    if (isSystemInDarkTheme) backgroundDark.toArgb()
                    else backgroundLight.toArgb()
                SideEffect {
                    (view.context as? Activity)?.window?.let { window ->
                        window.statusBarColor = backgroundColor
                        window.navigationBarColor = backgroundColor
                        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                            !isSystemInDarkTheme
                    }
                }
            }
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}