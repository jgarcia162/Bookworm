package jose.com.bookworm.presentations

import jose.com.bookworm.model.nytimes.BestSellersOverviewBook

interface FeedPresentation {
    fun showNotReadingAnyBooksText() //this text shows there aren't any books being read
    fun hideNotReadingAnyBooksText()
    fun showRefreshing() //show swipe to refresh layout
    fun hideRefreshing()
    fun showLoading()
    fun hideLoading()
    fun showBestSellersList(books: List<BestSellersOverviewBook>?)
    fun showGetBestSellersFailed()
    fun loadListNamesChips(names: MutableList<String>)
}
