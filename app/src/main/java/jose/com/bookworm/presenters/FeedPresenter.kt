package jose.com.bookworm.presenters

import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presentations.FeedPresentation

class FeedPresenter {
    private var presentation: FeedPresentation? = null
    private lateinit var apiClient: ApiClient

    fun attach(presentation: FeedPresentation) {
        this.presentation = presentation
    }

    fun detach() {
        presentation = null
    }

    fun getBestSellersOverview(){

    }
}
