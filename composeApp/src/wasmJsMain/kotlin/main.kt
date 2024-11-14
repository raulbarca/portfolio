import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.raulbarca.portfolio.App
import com.raulbarca.portfolio.di.appModule
import okio.FileSystem
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule)
    }
    CanvasBasedWindow("Portfolio") {
        /**
         * Disable disk cache for wasm-js target to avoid UnsupportedOperationException.
         * @see [FileSystem.SYSTEM_TEMPORARY_DIRECTORY]
         */
        App(disableDiskCache = true)
    }
}
