package jose.com.bookworm.room

import jose.com.bookworm.model.roommodel.Book
import javax.inject.Inject

class DatabaseHelper @Inject constructor(private val bookDao: BookDao) {
    fun getAllBooksByAuthor(author: String) = bookDao.getAllBooksByAuthor(author)

    fun getAllCurrentlyReading() = bookDao.getAllBooks().filter { it.isCurrentlyReading }

    fun getAllBorrowedBooks() = bookDao.getBorrowedBooks()

    fun getAllBooks() = bookDao.getAllBooks()

    fun addBook(book: Book) = bookDao.insertBook(book)

    fun updateBook(book: Book) = bookDao.updateBook(book)

    fun deleteBook(book: Book) = bookDao.deleteBook(book)

    fun getBookByISBN(isbn: Int): Book = bookDao.findBookByISBN(isbn)

    fun deleteAllBooks() = bookDao.deleteAllBooks()

    fun getBookByTitle(title: String): List<Book> = bookDao.getBookByTitle(title)

    fun getAllBooksBorrowedByUser(name: String): List<Book> = bookDao.getAllBooksBorrowedByUser(name)
}
