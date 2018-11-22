package jose.com.bookworm.network

import io.reactivex.Single
import jose.com.bookworm.model.nytimes.BestSellersListNamesResponse
import jose.com.bookworm.model.nytimes.BestSellersListResponse
import jose.com.bookworm.model.nytimes.BestSellersOverviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * DO NOT USE DIRECTLY. See [ApiClient]
 *
 * @author Jose Garcia jogarciadev@gmail.com 10/12/2018
 */
interface NYTimesApi {
    /**
     * Gets a best sellers list by name
     */
    @GET("/svc/books/v3/lists/current/current/{list-name}.json")
    fun getBestSellersList(
        @Path("list-name") listName: String,
        @Query("api-key") apiKey: String
    ): Single<BestSellersListResponse>

    /**
     * Gets top 5 books from each best sellers list
     */
    @GET("/svc/books/v3/lists/overview.json")
    fun getTopFiveBestSellers(
        @Query("api-key") apiKey: String
    ): Single<BestSellersOverviewResponse>

    /**
     * Gets list of all best seller overviewLists' names
     */
    @GET("/svc/books/v3/overviewLists/names.json")
    fun getBestSellersListNames(
        @Query("api-key") apiKey: String
    ): Single<BestSellersListNamesResponse>
}