package jose.com.bookworm.network

import io.reactivex.Single
import jose.com.bookworm.model.googlebooks.VolumesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    /** Gets Volumes by title */
    @GET("volumes/")
    fun searchByTitle(@Query("intitle:") title: String): Single<VolumesResponse>

    /** Gets Volumes by author */
    @GET("volumes/")
    fun searchByAuthor(@Query("inauthor:") author: String): Single<VolumesResponse>

    /** Gets Volume by ISBN */
    @GET("volumes/")
    fun searchByISBN(@Query("isbn:") isbn: String): Single<VolumesResponse>
}
