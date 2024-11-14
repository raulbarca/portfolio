import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.raulbarca.portfolio.App
import com.raulbarca.portfolio.di.appModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(appModule)
    }
    return application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Portfolio",
        ) {
            App()
        }
    }
}