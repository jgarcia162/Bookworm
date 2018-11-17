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
 * [GoogleBooksApi]
 * [NYTimesApi]
 *
 * Use ApiClient for easier abstraction and implementation should new APIs be introduced and for
 * cleaner integration with Dagger 2 framework.*/

class ApiClient(
    var loggingInterceptor: HttpLoggingInterceptor?
) {
    var booksBaseUrl: String = BOOKS_BASE_URL
        set(value) {
            field = value
            initBooksApi()
        }
    var nyTimesBaseUrl: String = NYTIMES_BASE_URL
        set(value) {
            field = value
            initNYTimesApi()
        }

    private lateinit var googleBooksApi: GoogleBooksApi
    private lateinit var nyTimesApi: NYTimesApi

    private fun initBooksApi() {
        googleBooksApi = createApi(booksBaseUrl)
    }

    private fun initNYTimesApi() {
        nyTimesApi = createApi(nyTimesBaseUrl)
    }

    private inline fun <reified T : Any> createApi(baseUrl: String): T {
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

    companion object {
        val BOOKS_API_KEY: String = BuildConfig.ApiKey
        val BOOKS_BASE_URL: String = "https://www.googleapis.com/books/v1/"
        val NYTIMES_API_KEY: String = BuildConfig.TimesApiKey
        val NYTIMES_BASE_URL: String = "http://api.nytimes.com/svc/books/v3/overviewLists"
    }

    /**
     * Gets [Volume]s by title
     *
     * See [GoogleBooksApi.searchByTitle]
     */
    fun searchByTitle(title: String) = googleBooksApi.searchByTitle(title)

    /**
     * Gets [Volume]s by author
     *
     * See [GoogleBooksApi.searchByAuthor]
     */
    fun searchByAuthor(author: String) = googleBooksApi.searchByAuthor(author)

    /**
     * Gets [Volume]s by ISBN
     *
     * See [GoogleBooksApi.searchByISBN]
     */
    fun searchByISBN(isbn: String) = googleBooksApi.searchByISBN(isbn)

    /**
     * Gets a [BestSellersList] by name
     *
     * See [NYTimesApi.getBestSellersList]
     */
    fun getBestSellersList(listName: String) = nyTimesApi.getBestSellersList(listName)

    /**
     * Gets a list of the top 5 [BestSellersBook] from each [TimesList]
     *
     * See [NYTimesApi.getTopFiveBestSellers]
     */
    fun getTopFiveBestSellers() = nyTimesApi.getTopFiveBestSellers()

    /**
     * Gets a list of all [TimesListName]s
     *
     * See [NYTimesApi.getBestSellersListNames]
     */
    fun getBestSellersListNames() = nyTimesApi.getBestSellersListNames()
}