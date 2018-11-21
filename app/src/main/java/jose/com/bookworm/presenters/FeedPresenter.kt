package jose.com.bookworm.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jose.com.bookworm.di.Injector
import jose.com.bookworm.model.nytimes.BestSellersOverviewResponse
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presentations.FeedPresentation

class FeedPresenter(
    private val apiClient: ApiClient
): BasePresenter() {
    private var presentation: FeedPresentation? = null
    private lateinit var compositeDisposable: CompositeDisposable

    fun attach(presentation: FeedPresentation) {
        this.presentation = presentation

        compositeDisposable = CompositeDisposable()

        Injector.applicationComponent.inject(this)

        getBestSellersOverview()
    }

    fun detach() {
        presentation = null
    }

    fun getBestSellersOverview(onLoadComplete: () -> Unit = {}){
        compositeDisposable += apiClient.getTopFiveBestSellers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                presentation?.showLoading()
            }
            .doAfterTerminate {
                presentation?.hideLoading()
                onLoadComplete()
            }
            .subscribeBy (
                onSuccess = { onGetBestSellersOverviewSuccess(it) },
                onError = { onGetBestSellersOverviewFailed() }
            )
    }

    private fun onGetBestSellersOverviewFailed() {
        presentation?.showGetBestSellersFailed()
    }

    private fun onGetBestSellersOverviewSuccess(it: BestSellersOverviewResponse?) {
        presentation?.showBestSellersList(it?.results?.overviewLists?.get(0)?.books)
    }

    override fun onitemClicked(item: Any) {

    }
}
