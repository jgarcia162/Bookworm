package jose.com.bookworm.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import jose.com.bookworm.model.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

    @Query("SELECT * FROM books WHERE isbn IS :isbn")
    fun findBookByISBN(isbn: Int)

    @Query("DELETE FROM books")
    fun deleteAllBooks()

    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE isInLibrary IS :borrowed")
    fun getBorrowedBooks(borrowed: Boolean): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE title LIKE :title")
    fun getBookByTitle(title: String) : LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE author LIKE :author")
    fun getAllBooksByAuthor(author: String): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE usr_name LIKE :name")
    fun getAllBooksBorrowedByUser(name: String): LiveData<List<Book>>
}