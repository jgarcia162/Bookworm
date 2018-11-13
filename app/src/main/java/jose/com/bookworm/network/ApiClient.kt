package jose.com.bookworm.network

import jose.com.bookworm.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/** [ApiClient] is a wrapper around the following APIs:
 * //TODO APIs used go here
 * Use ApiClient for easier abstraction and implementation should new APIs be introduced and for
 * cleaner integration with Dagger 2 framework.*/

class ApiClient(
    booksBaseUrl: String,
    var loggingInterceptor: HttpLoggingInterceptor?
) {
    var booksBaseUrl: String = booksBaseUrl
        set(value) {
            field = value
            initBooksApi()
        }

    private lateinit var booksApi: BooksApi

    private fun initBooksApi(){
        booksApi = createApi(booksBaseUrl)
    }

    private inline fun <reified T: Any> createApi(baseUrl: String): T{
        val builder = OkHttpClient.Builder()

        if(loggingInterceptor != null){
            builder.addInterceptor(this.loggingInterceptor!!)
        }

        val client = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(T::class.java)
    }

    init{
        initBooksApi()
    }

    //Temporarily placed here while testing/building
    companion object {
        val BOOKS_API_KEY: String = BuildConfig.ApiKey
        val BOOKS_BASE_URL: String = "https://www.googleapis.com/books/v1/"
    }

}