package jose.com.bookworm.presentations

interface LibraryPresentation {
    fun showBookDeleted()
    fun showLoading()
    fun hideLoading()
    fun showEmptyLibrary()
    fun hideEmptyLibrary()
    fun showSortedBooks()
}
