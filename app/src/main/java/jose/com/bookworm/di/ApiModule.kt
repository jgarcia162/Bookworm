package jose.com.bookworm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.network.CacheInterceptor
import jose.com.bookworm.network.ForceCacheInterceptor
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
  private const val cacheSize = (5 * 1024 * 1024).toLong()
  
  @Provides
  @Singleton
  fun provideNetworkCache(@ApplicationContext context: Context): Cache =
    Cache(context.cacheDir, cacheSize)
  
  @Provides
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BASIC
  }
  
  @Provides
  @Singleton
  fun provideCacheInterceptor(): CacheInterceptor = CacheInterceptor()
  
  @Provides
  @Singleton
  fun provideForceCacheInterceptor(@ApplicationContext context: Context) =
    ForceCacheInterceptor(context)
  
  @Provides
  @Singleton
  fun provideApiClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    cacheInterceptor: CacheInterceptor,
    forceCacheInterceptor: ForceCacheInterceptor,
    cache: Cache
  ): ApiClient {
    return ApiClient(httpLoggingInterceptor, cacheInterceptor, forceCacheInterceptor, cache)
  }
}