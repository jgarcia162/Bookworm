package jose.com.bookworm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import jose.com.bookworm.model.Book

class BookViewModel : ViewModel() {
  private val book = MutableLiveData<Book>()

  fun init(bookId: Long) {

  }

  fun getBook() = book

}
