package jose.com.bookworm.presentations

import jose.com.bookworm.model.Book

interface BookDetailsPresentation {
    fun showBookDeleted()
    fun showBookCheckedOut()
    fun showBookCheckedIn()
    fun showEditBookLayout()
    fun hideEditBookLayout()
    fun showBookDetails()
    fun updateBookData(book: Book)
}