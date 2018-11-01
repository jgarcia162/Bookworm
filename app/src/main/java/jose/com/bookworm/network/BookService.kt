package jose.com.bookworm.network

import io.reactivex.Observable
import jose.com.bookworm.model.Book
import retrofit2.http.GET

/** This class connects to the GoodReads API */
interface BookService {
  @GET("/books")
  fun getBooks(): Observable<List<Book>>
}
