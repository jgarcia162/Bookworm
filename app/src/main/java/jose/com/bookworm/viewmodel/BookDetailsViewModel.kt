package jose.com.bookworm.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jose.com.bookworm.network.ApiClient
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
  private val apiClient: ApiClient
) : ViewModel() {

  fun checkoutBook() {
    //TODO updates book status to checked out and adds borrower's info
  }

  fun checkInBook() {
    //TODO updates book to checked in
  }

  fun editBook() {
    //TODO edit book's details
  }

  fun deleteBook() {
    //TODO delete book from db
  }

  fun updateProgress() {
    //TODO updates book progress
  }

  fun onitemClicked(item: Any) {

  }

  override fun onCleared() {
    super.onCleared()
    //TODO cleanup
  }
}