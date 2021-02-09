package jose.com.bookworm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.repository.BookRepository
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
  private val repository: BookRepository,
  private val prefHelper: SharedPreferencesHelper
) : ViewModel() {
  private val categoriesLiveData = MutableLiveData<MutableSet<String>>(prefHelper.getCategories())
  
  fun addBook(
    title: String,
    author: String,
    isbn: String,
    pages: String,
    year: String,
    category: String
  ) {
    val defPages: Int = if (pages.isBlank()) {
      0
    } else {
      pages.toInt()
    }
    
    val defYear: Int = if (year.isBlank()) {
      0
    } else {
      year.toInt()
    }
    
    //TODO move to background thread
    repository.addBook(
      Book(
        title = title,
        author = author,
        isbn = isbn,
        pages = defPages,
        yearPublished = defYear,
        categories = category
      )
    )
  }
  
  fun getCategoriesLiveData() = categoriesLiveData
  
  override fun onCleared() {
    super.onCleared()
  }
}