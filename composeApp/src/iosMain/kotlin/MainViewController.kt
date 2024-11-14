import androidx.compose.ui.window.ComposeUIViewController
import com.raulbarca.portfolio.App
import com.raulbarca.portfolio.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(appModule)
        }
    }
) { App() }