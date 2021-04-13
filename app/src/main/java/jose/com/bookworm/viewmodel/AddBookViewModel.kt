package jose.com.bookworm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.repository.BookRepository
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AddBookViewModel @Inject constructor(
  private val repository: BookRepository,
  private val prefHelper: SharedPreferencesHelper,
  @Named("io") private val ioScheduler: Scheduler,
  @Named("main") private val mainThreadScheduler: Scheduler
) : ViewModel() {
  private val categoriesLiveData = MutableLiveData<MutableSet<String>>(prefHelper.getCategories())
  private val compositeDisposable: CompositeDisposable = CompositeDisposable()
  
  fun addBook(book: Book, onAddBookComplete: () -> Unit, onAddBookError: () -> Unit) {
    addBook(
      book.title,
      book.author,
      book.isbn,
      book.pages.toString(),
      book.yearPublished.toString(),
      book.categories,
      onAddBookComplete,
      onAddBookError
    )
  }
  
  fun addBook(
    title: String,
    author: String,
    isbn: String = "",
    pages: String = "",
    year: String = "",
    category: String = "",
    onAddBookComplete: () -> Unit = {},
    onAddBookError: () -> Unit = {},
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
    
    val book = Book(
      title = title,
      author = author,
      isbn = isbn,
      pages = defPages,
      yearPublished = defYear,
      categories = category
    )
    
    compositeDisposable += repository.addBook(book)
      .subscribeOn(ioScheduler)
      .observeOn(mainThreadScheduler)
      .subscribeBy(
        onComplete = {
          onAddBookComplete()
        },
        onError = {
          //TODO Display error
          onAddBookError()
          it.printStackTrace()
        }
      )
  }
  
  fun getCategoriesLiveData() = categoriesLiveData
  
  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}