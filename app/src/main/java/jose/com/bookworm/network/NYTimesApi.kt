package jose.com.bookworm.network

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
    suspend fun getBestSellersListAsync(
      @Query("api-key") apiKey: String,
      @Query("list") listName: String
    ): BestSellersListResponse
    
    /**
     * Gets top 5 books from each best sellers list
     */
    @GET("lists/overview.json")
    suspend fun getTopFiveBestSellersAsync(
      @Query("api-key") apiKey: String
    ): BestSellersOverviewResponse
    
    /**
     * Gets list of all best seller list names AKA categories
     */
    @GET("lists/names.json")
    suspend fun getBestSellersListNamesAsync(
      @Query("api-key") apiKey: String
    ): BestSellersListNamesResponse
}