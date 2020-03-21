package jose.com.bookworm.presenters

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxkotlin3.plusAssign
import io.reactivex.rxkotlin3.subscribeBy
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.di.Injector
import jose.com.bookworm.model.nytimes.BestSellersBook
import jose.com.bookworm.model.nytimes.BestSellersListName
import jose.com.bookworm.model.nytimes.BestSellersOverviewList
import jose.com.bookworm.model.nytimes.NYTimesBook
import jose.com.bookworm.presentations.FeedPresentation
import jose.com.bookworm.repository.BookRepository
import javax.inject.Inject
import javax.inject.Named

class FeedPresenter(
    private val repository: BookRepository,
    @Named("mainThreadScheduler") private val mainThreadScheduler: Scheduler,
    @Named("ioScheduler") private val ioScheduler: Scheduler
) : BasePresenter() {
    @Inject lateinit var prefHelper: SharedPreferencesHelper
    private var presentation: FeedPresentation? = null
    private lateinit var compositeDisposable: CompositeDisposable
    private val topBooks = mutableListOf<NYTimesBook>()
    private val listNameMap = mutableMapOf<String, String>()

    fun attach(presentation: FeedPresentation) {
        this.presentation = presentation

        Injector.applicationComponent.inject(this)

        compositeDisposable = CompositeDisposable()
    }

    fun detach() {
        presentation = null

        compositeDisposable.dispose()
    }

    fun getBestSellersOverview(onLoadComplete: () -> Unit = {}) {
        compositeDisposable += repository.getBestSellersOverview()
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
        compositeDisposable += repository.getBestSellersListNames()
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
        val listTitles = mutableSetOf<String>()
        for (name in listNames) {
            listTitles.add(name.displayName)
            listNameMap[name.displayName] = name.listName
        }
        prefHelper.saveCategories(listTitles)
        presentation?.loadListNamesChips(listTitles)
    }

    private fun onGetBestSellersListNamesFailed() {
        presentation?.showGetBestSellersFailed()
    }

    fun getBestSellersList(listName: String = "", onLoadComplete: () -> Unit = {}) {
        val books = mutableListOf<BestSellersBook>()
        compositeDisposable += repository.getBestSellersList(listNameMap[listName].toString())
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
                repository.getBestSellersList(listNameMap[it]!!)
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
