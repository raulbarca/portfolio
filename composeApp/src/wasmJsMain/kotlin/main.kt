import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.raulbarca.portfolio.App
import com.raulbarca.portfolio.di.appModule
import okio.FileSystem.Companion.SYSTEM_TEMPORARY_DIRECTORY
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule)
    }
    ComposeViewport(content = {
        /**
         * Disable disk cache for wasm-js target to avoid UnsupportedOperationException.
         * @see [SYSTEM_TEMPORARY_DIRECTORY]
         */
        App(disableDiskCache = true)
    })
}
