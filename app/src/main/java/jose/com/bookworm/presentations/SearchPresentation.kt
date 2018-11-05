package jose.com.bookworm.presentations

import jose.com.bookworm.model.Book

interface SearchPresentation {
    fun showNoResults()
    fun updateRV(results: List<Book>)
    fun showLoading()
    fun hideLoading()
    fun showSearchForBookText() //text telling the user to search by title, author, genre
    fun hideSearchForBookText()
}
