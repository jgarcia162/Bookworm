package jose.com.bookworm.presenters

import android.content.Context
import android.support.design.chip.Chip
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import jose.com.bookworm.model.nytimes.BestSellersBook
import jose.com.bookworm.model.nytimes.BestSellersListName
import jose.com.bookworm.model.nytimes.BestSellersOverviewList
import jose.com.bookworm.model.nytimes.NYTimesBook
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presentations.FeedPresentation
import timber.log.Timber

class FeedPresenter(
    private val context: Context,
    private val apiClient: ApiClient,
    private val mainThreadScheduler: Scheduler,
    private val ioScheduler: Scheduler
) : BasePresenter() {
    private var presentation: FeedPresentation? = null
    private lateinit var compositeDisposable: CompositeDisposable
    private val topBooks = mutableListOf<NYTimesBook>()

    fun attach(presentation: FeedPresentation) {
        this.presentation = presentation

        compositeDisposable = CompositeDisposable()
    }

    fun detach() {
        presentation = null

        compositeDisposable.dispose()
    }

    fun getBestSellersOverview(onLoadComplete: () -> Unit = {}) {
        compositeDisposable += apiClient.getTopFiveBestSellers()
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)
            .doOnSubscribe {
                presentation?.showLoading()
            }
            .doAfterTerminate {
                presentation?.hideLoading()
                onLoadComplete()
            }
            .subscribeBy(
                onSuccess = { onGetBestSellersOverviewSuccess(it) },
                onError = { onGetBestSellersOverviewFailed() }
            )
    }

    private fun onGetBestSellersOverviewSuccess(lists: List<BestSellersOverviewList>) {
        for (list in lists) {
            topBooks.addAll(list.books)
            Timber.d("FIRST BOOK ${topBooks[0].title}")
        }
        presentation?.showBestSellersList(topBooks)
        presentation?.showGetBestSellersSuccess("")
    }

    private fun onGetBestSellersOverviewFailed() {
        Timber.d("onGetBestSellersOverviewFailed")
        presentation?.showGetBestSellersFailed()
    }

    fun getBestSellersListNames(onLoadComplete: () -> Unit = {}) {
        compositeDisposable += apiClient.getBestSellersListNames()
            .map {
                it.results
            }
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)
            .doOnSubscribe {
                presentation?.showLoading()
            }
            .doAfterTerminate {
                presentation?.hideLoading()
                onLoadComplete()
            }
            .subscribeBy(
                onSuccess = { onGetBestSellersListNamesSuccess(it) },
                onError = { onGetBestSellersListNamesFailed() }
            )
    }

    private fun onGetBestSellersListNamesSuccess(listNames: List<BestSellersListName>) {
        val names = mutableListOf<String>()
        for (name in listNames) {
            names.add(name.displayName)
        }
        val chips = createChips(names)
        presentation?.loadListNamesChips(chips)
    }

    fun createChips(names: MutableList<String>): MutableList<Chip> {
        val chips = mutableListOf<Chip>()
        for (name in names) {
            val chip = Chip(context).apply {
                text = name
            }
            chips.add(chip)
        }
        return chips
    }

    private fun onGetBestSellersListNamesFailed() {
        presentation?.showGetBestSellersFailed()
    }

    fun getBestSellersList(listName: String = "", onLoadComplete: () -> Unit = {}) {
        val books = mutableListOf<BestSellersBook>()
        compositeDisposable += apiClient.getBestSellersList(listName)
            .map {
                for (item in it.results) {
                    books.add(item.bookDetails[0])
                }
            }
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)
            .doOnSubscribe {
                presentation?.showLoading()
            }
            .doAfterTerminate {
                presentation?.hideLoading()
                onLoadComplete()
            }
            .subscribeBy(
                onSuccess = { onGetBestSellersListSuccess(listName, books) },
                onError = { onGetBestSellersListFailed() }
            )
    }

    private fun onGetBestSellersListSuccess(listName: String, books: MutableList<BestSellersBook>) {
        presentation?.showBestSellersList(books)
        presentation?.showGetBestSellersSuccess(listName)
    }

    private fun onGetBestSellersListFailed() {

    }

    override fun onitemClicked(item: Any) {

    }
}
