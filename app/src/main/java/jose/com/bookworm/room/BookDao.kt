package jose.com.bookworm.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import jose.com.bookworm.model.roommodel.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book) : Completable

    @Update
    fun updateBook(book: Book) : Completable

    @Delete
    fun deleteBook(book: Book): Completable

    @Query("SELECT * FROM books WHERE isbn IS :isbn")
    fun findBookByISBN(isbn: String): Single<Book>

    @Query("DELETE FROM books")
    fun deleteAllBooks() : Completable

    @Query("SELECT * FROM books")
    fun getAllBooks(): Single<List<Book>>

    @Query("SELECT * FROM books WHERE isInLibrary = 1")
    fun getBorrowedBooks(): Single<List<Book>>

    @Query("SELECT * FROM books WHERE title LIKE :title")
    fun getBookByTitle(title: String) : Single<List<Book>>

    @Query("SELECT * FROM books WHERE author LIKE :author")
    fun getAllBooksByAuthor(author: String): Single<List<Book>>

    @Query("SELECT * FROM books WHERE usr_name LIKE :name")
    fun getAllBooksBorrowedByUser(name: String): Single<List<Book>>
}