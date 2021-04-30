package jose.com.bookworm.repository

import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.room.DatabaseHelper
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val databaseHelper: DatabaseHelper
) {
    fun getAllBooks() = databaseHelper.getAllBooks()

    fun addBook(book: Book) = databaseHelper.addBook(book)

    fun updateBook(book: Book) = databaseHelper.updateBook(book)

    fun deleteBook(book: Book) = databaseHelper.deleteBook(book)

    fun getBookByISBN(isbn: String) = databaseHelper.getBookByISBN(isbn)

    fun deleteAllBooks() = databaseHelper.deleteAllBooks()

    fun getBookByTitle(title: String) = databaseHelper.getBookByTitle(title)

    fun getAllBooksByAuthor(author: String) = databaseHelper.getAllBooksByAuthor(author)

    fun getAllBooksBorrowedByUser(name: String) = databaseHelper.getAllBooksBorrowedByUser(name)
    
    fun getAllBorrowedBooks() = databaseHelper.getAllBorrowedBooks()
    
    suspend fun getBestSellersOverviewAsync() = apiClient.getBestSellersOverviewAsync()
    
    suspend fun getBestSellersListNamesAsync() = apiClient.getBestSellersListNamesAsync()
    
    suspend fun getBestSellersList(listName: String) = apiClient.getBestSellersList(listName)
}
