package jose.com.bookworm.presenters

import jose.com.bookworm.presentations.FeedPresentation
import jose.com.bookworm.views.FeedFragment

class FeedPresenter {
    private var presentation: FeedPresentation? = null

    fun attach(presentation: FeedPresentation) {
        this.presentation = presentation
    }

    fun detach() {
        presentation = null
    }
}
