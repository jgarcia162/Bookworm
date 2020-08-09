package jose.com.bookworm.di

import dagger.Module
import dagger.Provides
import jose.com.bookworm.network.ApiClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module
class ApiModule{
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideApiClient(httpLoggingInterceptor: HttpLoggingInterceptor): ApiClient {
        return ApiClient(httpLoggingInterceptor)
    }
}