package jose.com.bookworm.presenters

import android.content.Context
import android.support.design.chip.Chip
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import jose.com.bookworm.model.nytimes.BestSellersListName
import jose.com.bookworm.model.nytimes.BestSellersListResponse
import jose.com.bookworm.model.nytimes.NYTimesBook
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presentations.FeedPresentation

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
    }

    fun getBestSellersOverview(onLoadComplete: () -> Unit = {}) {
        compositeDisposable += apiClient.getTopFiveBestSellers()
            .map { it.results.lists }
            .map {
                for (list in it) {
                    topBooks.addAll(list.books)
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
                onSuccess = { onGetBestSellersOverviewSuccess() },
                onError = { onGetBestSellersOverviewFailed() }
            )
    }

    private fun onGetBestSellersOverviewSuccess() {
        presentation?.showBestSellersList(topBooks)
    }

    private fun onGetBestSellersOverviewFailed() {
        presentation?.showGetBestSellersFailed()
    }

    private fun getBestSellersListNames(onLoadComplete: () -> Unit) {
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
        presentation?.showGetBestSellersSuccess("")
    }

    private fun createChips(names: MutableList<String>): MutableList<Chip> {
        val chips = mutableListOf<Chip>()
        for (name in names) {
            val newChip = Chip(context)
            newChip.text = name
            chips.add(newChip)
        }
        return chips
    }

    private fun onGetBestSellersListNamesFailed() {
        presentation?.showGetBestSellersFailed()
    }

    fun getBestSellersList(listName: String, onLoadComplete: () -> Unit) {
        compositeDisposable += apiClient.getBestSellersList(listName)
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
                onSuccess = { onGetBestSellersListSuccess(it) },
                onError = { onGetBestSellersListFailed() }
            )
    }

    private fun onGetBestSellersListSuccess(it: BestSellersListResponse?) {

    }

    private fun onGetBestSellersListFailed() {

    }

    override fun onitemClicked(item: Any) {

    }
}
