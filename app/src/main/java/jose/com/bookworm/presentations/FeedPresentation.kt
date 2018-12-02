package jose.com.bookworm.presentations

import jose.com.bookworm.model.nytimes.NYTimesBook

interface FeedPresentation {
    fun showNotReadingAnyBooksText() //this text shows there aren't any books being read
    fun hideNotReadingAnyBooksText()
    fun showRefreshing() //show swipe to refresh layout
    fun hideRefreshing()
    fun showLoading()
    fun hideLoading()
    fun showBestSellersList(books: List<NYTimesBook>)
    fun showBestSellersListFailed()
    fun showGetBestSellersFailed()
    fun showGetBestSellersSuccess(listName: String = "")
    fun loadListNamesChips(listTitles: MutableList<String>)
    fun showNoResults()
    fun getBestSellerList(listName: String)
    fun getMultipleLists(listNames: List<String>)
}
