package jose.com.bookworm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.extensions.logStackTrace
import jose.com.bookworm.model.nytimes.*
import jose.com.bookworm.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FeedViewModel @Inject constructor(
  private val repository: BookRepository,
  private val prefHelper: SharedPreferencesHelper,
  @Named("main") private val mainThreadScheduler: Scheduler,
  @Named("io") private val ioScheduler: Scheduler
) : ViewModel() {
  private var compositeDisposable: CompositeDisposable = CompositeDisposable()
  private val topBooks = mutableListOf<BestSellersOverviewBook>()
  private val categoriesMap = mutableMapOf<String, String>()
  private val isLoadingLiveData = MutableLiveData<Boolean>()
  
  private val listTitlesLiveData = MutableLiveData<MutableSet<String>>()
  val listTitles: LiveData<MutableSet<String>> = listTitlesLiveData
  
  private val bestSellersListLiveData = MutableLiveData<List<BestSellersOverviewBook>>()
  val bestSellersList: LiveData<List<BestSellersOverviewBook>> = bestSellersListLiveData
  
  private val bestSellersLiveData = MutableLiveData<List<BestSellersBook>>()
  
  private val isEmptyLiveData = MutableLiveData(true)
  
  //uses a Pair object to return the success status and the name of the list that was loaded/failed
  private val isSuccessfulLiveData = MutableLiveData(Pair(true, ""))
  
  suspend fun getBestSellersOverview(
    onLoadComplete: () -> Unit = {},
    onLoadFailed: (Throwable) -> Unit = {}
  ) {
    isLoadingLiveData.value = true
    viewModelScope.launch {
      try {
        val results = repository.getBestSellersOverviewAsync()
        isLoadingLiveData.value = false
        onGetBestSellersOverviewSuccess(results.results.lists)
        onLoadComplete()
        
      } catch (error: Throwable) {
        onGetBestSellersOverviewFailed(error, onLoadFailed)
        error.logStackTrace()
      }
    }
  }
  
  private fun onGetBestSellersOverviewSuccess(lists: List<BestSellersOverviewList>) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        addBooksToList(lists)
      }
  
      bestSellersListLiveData.postValue(topBooks)
      isSuccessfulLiveData.value = Pair(true, "")
    }
  }
  
  private fun addBooksToList(lists: List<BestSellersOverviewList>) {
    for (list in lists) {
      for (book in list.books) {
        val filteredList = topBooks.filter {
          it.title == book.title
        }
  
        if (filteredList.isEmpty()) {
          book.category = list.displayName
          topBooks.add(book)
        }
      }
    }
  }
  
  private fun onGetBestSellersOverviewFailed(
    error: Throwable, onLoadFailed: (Throwable) -> Unit = {}
  ) {
    isSuccessfulLiveData.value = Pair(false, "")
    onLoadFailed(error)
  }
  
  suspend fun getBestSellersListNames(
    onLoadComplete: () -> Unit = {},
    onLoadFailed: (Throwable) -> Unit = {}
  ) {
    isLoadingLiveData.postValue(true)
    viewModelScope.launch {
      val list = repository.getBestSellersListNamesAsync()
      try {
        isLoadingLiveData.postValue(false)
        onGetBestSellersListNamesSuccess(list.results)
        onLoadComplete()
        
      } catch (error: Throwable) {
        onGetBestSellersListNamesFailed(error, onLoadFailed)
        error.logStackTrace()
      }
    }
  }
  
  private fun onGetBestSellersListNamesSuccess(listNames: List<BestSellersListName>) {
    val listTitles = mutableSetOf<String>()
    for (name in listNames) {
      listTitles.add(name.displayName)
      categoriesMap[name.displayName] = name.listName
    }
    prefHelper.saveCategories(listTitles)
    listTitlesLiveData.postValue(listTitles)
  }
  
  private fun onGetBestSellersListNamesFailed(
    error: Throwable,
    onLoadFailed: (Throwable) -> Unit = {}
  ) {
    isSuccessfulLiveData.value = Pair(false, "")
    onLoadFailed(error)
  }
  
  fun getBestSellersList(listName: String = "", onLoadComplete: () -> Unit = {}) {
    isLoadingLiveData.postValue(true)
    val books = mutableListOf<BestSellersBook>()
    viewModelScope.launch {
      val list = repository.getBestSellersList(categoriesMap[listName].toString())
      
      list.results.forEach {
        val element = it.listOfBooks[0].copy(category = it.listName)
        books.add(element)
      }
      withContext(Dispatchers.Main) {
        isLoadingLiveData.postValue(false)
        onLoadComplete()
        onGetBestSellersListSuccess(listName, books)
      }
    }
  }
  
  private fun onGetBestSellersListSuccess(listName: String, books: MutableList<BestSellersBook>) {
    if (books.size < 1) {
      isEmptyLiveData.postValue(true)
    } else {
      bestSellersLiveData.postValue(books)
      isSuccessfulLiveData.postValue(Pair(true, listName))
    }
  }
  
  private fun onGetBestSellersListFailed(listName: String) {
    isSuccessfulLiveData.postValue(Pair(false, listName))
  }
  
  /**
   * Gets a list of books of the given list of types. This is used to filter books by types
   *
   * @param listNames - a Set of Strings. The types of books to filter for.
   * @param onLoadComplete - callback function invoked when the network call succeeds or fails
   * */
  fun getMultipleLists(listNames: Set<String>, onLoadComplete: () -> Unit = {}) {
    val books = mutableListOf<BestSellersBook>()
    val observable: Observable<String> = Observable.fromIterable(listNames)
    compositeDisposable += observable
      .subscribeOn(ioScheduler)
      .observeOn(mainThreadScheduler)
      .doOnSubscribe {
        isLoadingLiveData.postValue(true)
      }
      .doOnTerminate {
        isLoadingLiveData.postValue(false)
        onLoadComplete()
      }
      //map over the list of categories
      .map { category ->
        var categories: List<BestSellersListItem> = listOf()
        viewModelScope.launch {
          //for each category make a network call to get the list of books of that type
          val list = repository.getBestSellersList(categoriesMap[category]!!)
          //assign categories to list
          categories = list.results
        }
        categories.toObservable()
      }
      .map {
        //add only the first book from each category
        it.blockingIterable().forEach { category ->
          books.add(category.listOfBooks[0])
        }
        
        Observable.just(books)
      }
      .subscribeBy(
        onComplete = { onGetMultipleListsSuccess(books) },
        onError = { onGetMultipleListsFailed() }
      )
  }
  
  private fun onGetMultipleListsSuccess(books: MutableList<BestSellersBook>) {
    bestSellersLiveData.postValue(books)
  }
  
  private fun onGetMultipleListsFailed() {
    isSuccessfulLiveData.postValue(Pair(false, ""))
  }
  
  fun getCurrentReadings() {
  
  }
  
  /**
   * Returns a reference to the [LiveData] object to observe for loading state.*/
  fun getIsLoadingLiveData(): MutableLiveData<Boolean> = isLoadingLiveData
  
  fun getIsEmptyLiveData(): MutableLiveData<Boolean> = isEmptyLiveData
  
  fun getIsSuccessfulLiveData(): MutableLiveData<Pair<Boolean, String>> = isSuccessfulLiveData
  
  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
