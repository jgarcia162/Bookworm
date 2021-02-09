package jose.com.bookworm.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.network.ApiClient
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val apiClient: ApiClient
) : ViewModel() {
  
  fun searchForBook(searchString: String) {
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
  
  private fun updateSearchResults(results: List<Book>) {
    //TODO pass results back to presentation and populate RV
  }
  
  fun onitemClicked(item: Any) {
  
  }
  
  override fun onCleared() {
    super.onCleared()
    
    //TODO cleanup
  }
}