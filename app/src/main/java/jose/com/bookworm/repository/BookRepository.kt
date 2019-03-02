package jose.com.bookworm.repository

import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.room.DatabaseHelper

class BookRepository(
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

    fun getAllCurrentlyReading() = databaseHelper.getAllCurrentlyReading()

    fun getAllBorrowedBooks() = databaseHelper.getAllBorrowedBooks()

    fun getBestSellersOverview() = apiClient.getBestSellersOverview()

    fun getBestSellersListNames() = apiClient.getBestSellersListNames()

    fun getBestSellersList(listName: String) = apiClient.getBestSellersList(listName)
}
