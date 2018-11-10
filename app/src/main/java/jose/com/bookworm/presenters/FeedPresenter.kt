package jose.com.bookworm.presenters

import jose.com.bookworm.presentations.FeedPresentation

class FeedPresenter {
    private var presentation: FeedPresentation? = null
    fun attach(presentation: FeedPresentation) {
        this.presentation = presentation
    }

    fun detach() {
        presentation = null
    }
}
