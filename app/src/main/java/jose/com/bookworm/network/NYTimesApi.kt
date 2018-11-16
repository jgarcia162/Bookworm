package jose.com.bookworm.network

import jose.com.bookworm.model.nytimes.TimesBookResponse
import retrofit2.http.GET

interface NYTimesApi {

    @GET(" /lists/best-sellers/history.json")
    fun getBestSellers(): TimesBookResponse
}