package jose.com.bookworm.presenters

import jose.com.bookworm.di.Injector
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presentations.LibraryPresentation

class LibraryPresenter(
    private val apiClient: ApiClient
): BasePresenter() {
    private var presentation: LibraryPresentation? = null

    fun attach(presentation: LibraryPresentation){
        this.presentation = presentation

        Injector.applicationComponent.inject(this)
    }

    fun detach(){
        presentation = null
    }

    fun deleteBook(book: Book){
        //TODO deletes book from db
    }

    fun deleteAllBooks(){
        //TODO deletes all books from db
    }

    fun showBookDetails(book: Book){
        presentation?.showBookDetails(book)
    }

    fun sortBooks(sortBy: String){
        //TODO sort books
    }

    fun searchBook(searchTerms: String){
        //TODO search by text
    }

    override fun onitemClicked(item: Any) {

    }
}