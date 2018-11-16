package jose.com.bookworm.network

import io.reactivex.Single
import jose.com.bookworm.model.VolumesResponse
import jose.com.bookworm.model.googlebooks.Volume
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    /** Gets Volumes by title */
    @GET("volumes/")
    fun searchByTitle(@Query("intitle:") title: String ): Single<VolumesResponse>

    /** Gets Volumes by author */
    @GET("volumes/{author}")
    fun searchByAuthor(@Query("inauthor:") author: String ): List<Volume>

    /** Gets Volume by ISBN */
    @GET("volumes/{isbn}")
    fun searchByISBN(@Query("isbn:") isbn: String ): List<Volume>
}
