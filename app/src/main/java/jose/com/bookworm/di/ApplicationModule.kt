package jose.com.bookworm.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import jose.com.bookworm.network.ApiClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module(includes = [DaoModule::class])
class ApplicationModule(val app: Application) {

    @Provides
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor{ it ->
            Timber.d("LOGGER: $it")
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return ApiClient(
            loggingInterceptor = httpLoggingInterceptor
        )
    }
}
