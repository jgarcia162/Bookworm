package jose.com.bookworm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.repository.BookRepository
import javax.inject.Inject
import javax.inject.Named
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy


@HiltViewModel
class LibraryViewModel @Inject constructor(
  @Named("main") private val mainThreadScheduler: Scheduler,
  @Named("io") private val ioScheduler: Scheduler,
  private val bookRepository: BookRepository
) : ViewModel() {
  
  private var compositeDisposable: CompositeDisposable = CompositeDisposable()
  private var booksLiveData: MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
  private var isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
  
  fun getAllBooks(onGetBooksComplete: () -> Unit = {}) {
    compositeDisposable += bookRepository.getAllBooks()
      .subscribeOn(ioScheduler)
      .observeOn(mainThreadScheduler)
      .doOnSubscribe {
        //TODO set loading to true
      }
      .doOnTerminate {
        //TODO set loading to false
      }
      .subscribeBy(
        onSuccess = {
          booksLiveData.value = it
          onGetBooksComplete()
        },
        onError = {
          //TODO display error
        }
      )
  }
  
  fun getBestSellersOverview(onLoadComplete: () -> Unit = {}) {
//    compositeDisposable += bookRepository.getAllBooks()
//      .subscribeOn(ioScheduler)
//      .observeOn(mainThreadScheduler)
//      .doOnSubscribe {
//        isLoadingLiveData.value = true
//      }
//      .doAfterTerminate {
//        isLoadingLiveData.value = false
//        onLoadComplete()
//      }
//      .subscribeBy(
//        onSuccess = { onGetBestSellersOverviewSuccess(it) },
//        onError = { onGetBestSellersOverviewFailed() }
//      )
  }
  
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
  
  fun getBooks() = booksLiveData
  fun getIsLoading() = isLoadingLiveData
}
