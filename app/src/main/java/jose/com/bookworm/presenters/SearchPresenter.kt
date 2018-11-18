package jose.com.bookworm.presenters

import jose.com.bookworm.di.Injector
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presentations.SearchPresentation

class SearchPresenter(
    private val apiClient: ApiClient
): BasePresenter() {
    private var presentation: SearchPresentation? = null

    fun attach(presentation: SearchPresentation) {
        this.presentation = presentation

        Injector.applicationComponent.inject(this)
    }

    fun detach(){
        presentation = null
    }

    fun searchForBook(searchString: String){
        searchByTitle(searchString)
        searchByAuthor(searchString)
        searchByGenre(searchString)
    }

    private fun searchByGenre(searchString: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun searchByAuthor(searchString: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun searchByTitle(searchString: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun updateSearchResults(results: List<Book>){
        //TODO pass results back to presentation and populate RV
    }

    override fun onitemClicked(item: Any) {

    }
}
