package jose.com.bookworm.network

import io.reactivex.Single
import jose.com.bookworm.model.nytimes.BestSellersListNamesResponse
import jose.com.bookworm.model.nytimes.BestSellersListResponse
import jose.com.bookworm.model.nytimes.BestSellersOverviewResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * DO NOT USE DIRECTLY. See [ApiClient]
 *
 * @author Jose Garcia jogarciadev@gmail.com 10/12/2018
 */
interface NYTimesApi {
    /**
     * Gets a best sellers list by name
     *
     * "https://api.nytimes.com/svc/books/v3/lists/overview.json?api-key=3f015948418c4a2383be12847ff477f1
     */
    @GET("lists.json")
    fun getBestSellersList(
        @Query("api-key") apiKey: String,
        @Query("list") listName: String
    ): Single<BestSellersListResponse>

    /**
     * Gets top 5 books from each best sellers list
     */
    @GET("lists/overview.json")
    fun getTopFiveBestSellers(
        @Query("api-key") apiKey: String
    ): Single<BestSellersOverviewResponse>

    /**
     * Gets list of all best seller list names
     */
    @GET("lists/names.json")
    fun getBestSellersListNames(
        @Query("api-key") apiKey: String
    ): Single<BestSellersListNamesResponse>
}