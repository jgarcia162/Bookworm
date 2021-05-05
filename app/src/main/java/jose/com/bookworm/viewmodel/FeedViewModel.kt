package jose.com.bookworm.viewmodel

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
import jose.com.bookworm.model.nytimes.*
import jose.com.bookworm.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
  private val listNameMap = mutableMapOf<String, String>()
  private val isLoadingLiveData = MutableLiveData<Boolean>()
  private val listTitlesLiveData = MutableLiveData<MutableSet<String>>()
  private val bestSellersListLiveData = MutableLiveData<List<BestSellersOverviewBook>>()
  private val bestSellersLiveData = MutableLiveData<List<BestSellersBook>>()
  private val isEmptyLiveData = MutableLiveData(true)
  private val isSuccessfulLiveData = MutableLiveData(Pair(true, ""))
  private var job: Job = Job()
  
  suspend fun getBestSellersOverview(onLoadComplete: () -> Unit = {}) {
    isLoadingLiveData.value = true
    job = viewModelScope.launch {
      val results = repository.getBestSellersOverviewAsync()
      isLoadingLiveData.value = false
      onGetBestSellersOverviewSuccess(results.results.lists)
      onLoadComplete()
    }
  }
  
  private fun onGetBestSellersOverviewSuccess(lists: List<BestSellersOverviewList>) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        addBooksToList(lists)
      }
      
      bestSellersListLiveData.value = topBooks
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
          topBooks.add(book)
        }
      }
    }
  }
  
  private fun onGetBestSellersOverviewFailed() {
    isSuccessfulLiveData.value = Pair(false, "")
  }
  
  fun getBestSellersListNames(onLoadComplete: () -> Unit = {}) {
    isLoadingLiveData.postValue(true)
    job = viewModelScope.launch {
      val list = repository.getBestSellersListNamesAsync()
      withContext(Dispatchers.Main) {
        isLoadingLiveData.postValue(false)
        onGetBestSellersListNamesSuccess(list.results)
        onLoadComplete()
      }
    }
  }
  
  private fun onGetBestSellersListNamesSuccess(listNames: List<BestSellersListName>) {
    val listTitles = mutableSetOf<String>()
    for (name in listNames) {
      listTitles.add(name.displayName)
      listNameMap[name.displayName] = name.listName
    }
    prefHelper.saveCategories(listTitles)
    listTitlesLiveData.postValue(listTitles)
  }
  
  private fun onGetBestSellersListNamesFailed() {
    isSuccessfulLiveData.value = Pair(false, "")
  }
  
  fun getBestSellersList(listName: String = "", onLoadComplete: () -> Unit = {}) {
    isLoadingLiveData.postValue(true)
    val books = mutableListOf<BestSellersBook>()
    job = viewModelScope.launch {
      val list = repository.getBestSellersList(listNameMap[listName].toString())
    
      list.results.forEach {
        books.add(it.bookDetails[0])
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
      .map {
        var listItems: List<BestSellersListItem> = listOf()
        viewModelScope.launch {
          val list = repository.getBestSellersList(listNameMap[it]!!)
          listItems = list.results
        }
        listItems.toObservable()
      }
      .map {
        it.blockingIterable().forEach { item ->
          books.add(item.bookDetails[0])
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
  
  fun getIsLoadingLiveData(): MutableLiveData<Boolean> = isLoadingLiveData
  
  fun getListTitlesLiveData(): MutableLiveData<MutableSet<String>> = listTitlesLiveData
  
  fun getBestSellersListLiveData(): MutableLiveData<List<BestSellersOverviewBook>> =
    bestSellersListLiveData
  
  fun getIsEmptyLiveData(): MutableLiveData<Boolean> = isEmptyLiveData
  
  fun getIsSuccessfulLiveData(): MutableLiveData<Pair<Boolean, String>> = isSuccessfulLiveData
  
  override fun onCleared() {
    super.onCleared()
    job.cancel()
    compositeDisposable.clear()
  }
}
