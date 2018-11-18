package jose.com.bookworm.presenters

import jose.com.bookworm.di.Injector
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presentations.BookDetailsPresentation

class BookDetailsPresenter(
    private val apiClient: ApiClient
): BasePresenter() {
    private var presentation: BookDetailsPresentation? = null

    fun attach(presentation: BookDetailsPresentation){
        this.presentation = presentation

        Injector.applicationComponent.inject(this)
    }

    fun detach(){
        presentation = null
    }

    fun checkoutBook(){
        //TODO updates book status to checked out and adds borrower's info
    }

    fun checkInBook(){
        //TODO updates book to checked in
    }

    fun editBook(){
        //TODO edit book's details
    }

    fun deleteBook(){
        //TODO delete book from db
    }

    fun updateProgress(){
        //TODO updates book progress
    }

    override fun onitemClicked(item: Any) {

    }
}