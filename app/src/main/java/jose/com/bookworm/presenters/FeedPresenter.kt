package jose.com.bookworm.presenters

import android.content.Context
import io.reactivex.Observable
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

class FeedPresenter(
    private val context: Context,
    private val apiClient: ApiClient,
    private val mainThreadScheduler: Scheduler,
    private val ioScheduler: Scheduler
) : BasePresenter() {
    private var presentation: FeedPresentation? = null
    private lateinit var compositeDisposable: CompositeDisposable
    private val topBooks = mutableListOf<NYTimesBook>()
    private val listNameMap = mutableMapOf<String, String>()

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
        }
        presentation?.showBestSellersList(topBooks)
        presentation?.showGetBestSellersSuccess()
    }

    private fun onGetBestSellersOverviewFailed() {
        presentation?.showGetBestSellersFailed()
    }

    fun getBestSellersListNames(onLoadComplete: () -> Unit = {}) {
        compositeDisposable += apiClient.getBestSellersListNames()
            .map {
                it.results
            }
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)
            .doAfterTerminate {
                onLoadComplete()
            }
            .subscribeBy(
                onSuccess = { onGetBestSellersListNamesSuccess(it) },
                onError = { onGetBestSellersListNamesFailed() }
            )
    }

    private fun onGetBestSellersListNamesSuccess(listNames: List<BestSellersListName>) {
        val listTitles = mutableListOf<String>()
        for (name in listNames) {
            listTitles.add(name.displayName)
            listNameMap[name.displayName] = name.listName
        }
        presentation?.loadListNamesChips(listTitles)
    }

    private fun onGetBestSellersListNamesFailed() {
        presentation?.showGetBestSellersFailed()
    }

    fun getBestSellersList(listName: String = "", onLoadComplete: () -> Unit = {}) {
        val books = mutableListOf<BestSellersBook>()
        compositeDisposable += apiClient.getBestSellersList(listNameMap[listName].toString())
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
                onError = { onGetBestSellersListFailed(listName) }
            )
    }

    private fun onGetBestSellersListSuccess(listName: String, books: MutableList<BestSellersBook>) {
        if (books.size < 1) {
            presentation?.showNoResults()
        } else {
            presentation?.showBestSellersList(books)
            presentation?.showGetBestSellersSuccess(listName)
        }
    }

    private fun onGetBestSellersListFailed(listName: String) {
        presentation?.showBestSellersListFailed(listName)
    }

    fun getMultipleLists(listNames: Set<String>, onLoadComplete: () -> Unit = {}) {
        val books = mutableListOf<BestSellersBook>()
        val observable: Observable<String> = Observable.fromIterable(listNames)
        compositeDisposable += observable
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)
            .doOnSubscribe {
                presentation?.showLoading()
            }
            .doOnTerminate {
                presentation?.hideLoading()
                onLoadComplete()
            }
            .flatMap {
                apiClient.getBestSellersList(listNameMap[it]!!)
                    .subscribeOn(ioScheduler)
                    .observeOn(mainThreadScheduler)
                    .toObservable()
            }
            .flatMap {
                for (item in it.results) {
                    books.add(item.bookDetails[0])
                }
                Observable.just(books)
            }
            .subscribeBy(
                onComplete = { onGetMultipleListsSuccess(books) },
                onError = { onGetMultipleListsFailed() }
            )
    }

    private fun onGetMultipleListsSuccess(books: MutableList<BestSellersBook>) {
        presentation?.showBestSellersList(books)
    }

    private fun onGetMultipleListsFailed() {
        presentation?.showGetBestSellersFailed()
    }

    override fun onitemClicked(item: Any) {

    }

    fun getCurrentReadings() {

    }
}
