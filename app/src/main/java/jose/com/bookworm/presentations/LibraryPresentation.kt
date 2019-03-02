package jose.com.bookworm.presentations

import jose.com.bookworm.model.roommodel.Book

interface LibraryPresentation {
    fun showBookDeleted()
    fun showLoading()
    fun hideLoading()
    fun showEmptyLibrary()
    fun hideEmptyLibrary()
    fun showSortedBooks()
    fun showBookDetails(book: Book)
}
