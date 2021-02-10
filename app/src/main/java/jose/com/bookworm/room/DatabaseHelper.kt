package jose.com.bookworm.room

import io.reactivex.rxjava3.core.Single
import jose.com.bookworm.model.roommodel.Book
import javax.inject.Inject

class DatabaseHelper @Inject constructor(private val bookDao: BookDao) {
    fun getAllBooksByAuthor(author: String) = bookDao.getAllBooksByAuthor(author)

    //TODO reimplement this
//    fun getAllCurrentlyReading()  = bookDao.getAllBooks().filter { it.isCurrentlyReading }

    fun getAllBorrowedBooks() = bookDao.getBorrowedBooks()

    fun getAllBooks() = bookDao.getAllBooks()

    fun addBook(book: Book) = bookDao.insertBook(book)

    fun updateBook(book: Book) = bookDao.updateBook(book)

    fun deleteBook(book: Book) = bookDao.deleteBook(book)

    fun getBookByISBN(isbn: String): Single<Book> = bookDao.findBookByISBN(isbn)

    fun deleteAllBooks() = bookDao.deleteAllBooks()

    fun getBookByTitle(title: String): Single<List<Book>> = bookDao.getBookByTitle(title)

    fun getAllBooksBorrowedByUser(name: String): Single<List<Book>> = bookDao.getAllBooksBorrowedByUser(name)
}
