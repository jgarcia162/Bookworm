package jose.com.bookworm.network

import jose.com.bookworm.BuildConfig
import jose.com.bookworm.model.googlebooks.Volume
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/** This is a wrapper around the following APIs:
 *
 * [BooksApi]
 * [NYTimesApi]
 *
 * Use ApiClient for easier abstraction and implementation should new APIs be introduced and for
 * cleaner integration with Dagger 2 framework.*/

class ApiClient(
    booksBaseUrl: String,
    nyTimesBaseUrl: String,
    var loggingInterceptor: HttpLoggingInterceptor?
) {
    var booksBaseUrl: String = booksBaseUrl
        set(value) {
            field = value
            initBooksApi()
        }
    var nyTimesBaseUrl: String = nyTimesBaseUrl
        set(value) {
            field = value
            initNYTimesApi()
        }

    private lateinit var booksApi: BooksApi
    private lateinit var nyTimesApi: NYTimesApi

    private fun initBooksApi() {
        booksApi = createApi(booksBaseUrl)
    }

    private fun initNYTimesApi(){
        nyTimesApi = createApi(nyTimesBaseUrl)
    }

    private inline fun <reified T: Any> createApi(baseUrl: String): T{
        val builder = OkHttpClient.Builder()

        if (loggingInterceptor != null) {
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

    init {
        initBooksApi()
        initNYTimesApi()
    }

    //Temporarily placed here while testing/building
    companion object {
        val BOOKS_API_KEY: String = BuildConfig.ApiKey
        val BOOKS_BASE_URL: String = "https://www.googleapis.com/books/v1/"
        val NYTIMES_API_KEY: String = BuildConfig.TimesApiKey
        val NYTIMES_BASE_URL: String = "https://www.googleapis.com/books/v1/"
    }

    /**
     * Gets [Volume]s by title
     *
     * See [BooksApi.searchByTitle]
     */
    fun searchByTitle(title: String) = booksApi.searchByTitle(title)


    /**
     * Gets [Volume]s by author
     *
     * See [BooksApi.searchByAuthor]
     */
    fun searchByAuthor(author: String) = booksApi.searchByAuthor(author)

    /**
     * Gets [Volume]s by ISBN
     *
     * See [BooksApi.searchByISBN]
     */
    fun searchByISBN(isbn: String) = booksApi.searchByISBN(isbn)


    /**
     * Gets list of best sellers
     *
     * See [NYTimesApi.getBestSellers]
     */
    fun getBestSellers() = nyTimesApi.getBestSellers()
}