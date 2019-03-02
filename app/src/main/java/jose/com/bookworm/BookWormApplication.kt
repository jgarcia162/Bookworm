package jose.com.bookworm

import android.app.Application
import timber.log.Timber


class BookWormApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        APP_CONTEXT = this
        Timber.plant(Timber.DebugTree())

//        RxJavaPlugins.setErrorHandler {
//            Timber.e(it.message)
//        }
    }

    companion object {
        lateinit var APP_CONTEXT: BookWormApplication
            private set
    }
}