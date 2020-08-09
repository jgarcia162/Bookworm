package jose.com.bookworm.viewmodel

import androidx.lifecycle.ViewModel
import jose.com.bookworm.model.roommodel.Book
import javax.inject.Inject

class LibraryViewModel @Inject constructor(): ViewModel() {
  
  fun deleteBook(book: Book) {
    //TODO deletes book from db
  }
  
  fun deleteAllBooks() {
    //TODO deletes all books from db
  }
  
  fun showBookDetails(book: Book) {
  }
  
  fun sortBooks(sortBy: String) {
    //TODO sort books
  }
  
  fun searchBook(searchTerms: String) {
    //TODO search by text
  }
  
  fun onitemClicked(item: Any) {
  
  }
}