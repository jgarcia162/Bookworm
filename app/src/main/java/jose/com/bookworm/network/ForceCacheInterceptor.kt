package jose.com.bookworm.network

import android.content.Context
import jose.com.bookworm.extensions.isConnected
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Forces the data request to be returned from the cache when there is no network available, unless
 * the cached data has gone stale. See [CacheInterceptor] for stale time limit
 * */
class ForceCacheInterceptor(val context: Context) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val builder: Request.Builder = chain.request().newBuilder()
    if (!context.isConnected) {
      builder.cacheControl(CacheControl.FORCE_CACHE)
    }
    return chain.proceed(builder.build())
  }
}