package jose.com.bookworm.presentations

import android.support.design.chip.Chip
import jose.com.bookworm.model.nytimes.NYTimesBook

interface FeedPresentation {
    fun showNotReadingAnyBooksText() //this text shows there aren't any books being read
    fun hideNotReadingAnyBooksText()
    fun showRefreshing() //show swipe to refresh layout
    fun hideRefreshing()
    fun showLoading()
    fun hideLoading()
    fun showBestSellersList(books: List<NYTimesBook>)
    fun showBestSellersListSuccess(listName: String)
    fun showBestSellersListFailed()
    fun showGetBestSellersFailed()
    fun showGetBestSellersSuccess(title: String)
    fun loadListNamesChips(names: MutableList<Chip>)
}
