package jose.com.bookworm.presenters

import jose.com.bookworm.di.Injector
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presentations.FeedPresentation

class FeedPresenter(
    private val apiClient: ApiClient
) {
    private var presentation: FeedPresentation? = null

    fun attach(presentation: FeedPresentation) {
        this.presentation = presentation

        Injector.applicationComponent.inject(this)

    }

    fun detach() {
        presentation = null
    }

    fun getBestSellersOverview(){

    }
}
