package jose.com.bookworm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.model.nytimes.BestSellersBook
import jose.com.bookworm.model.nytimes.BestSellersListName
import jose.com.bookworm.model.nytimes.BestSellersOverviewList
import jose.com.bookworm.model.nytimes.NYTimesBook
import jose.com.bookworm.repository.BookRepository
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class FeedViewModel
@Inject constructor(
  private val repository: BookRepository,
  private val prefHelper: SharedPreferencesHelper,
  @Named("main") private val mainThreadScheduler: Scheduler,
  @Named("io") private val ioScheduler: Scheduler
) : ViewModel() {
  private var compositeDisposable: CompositeDisposable = CompositeDisposable()
  private val topBooks = mutableListOf<NYTimesBook>()
  private val listNameMap = mutableMapOf<String, String>()
  
  private val isLoadingLiveData = MutableLiveData<Boolean>()
  private val listTitlesLiveData = MutableLiveData<MutableSet<String>>()
  private val bestSellersListLiveData = MutableLiveData<List<NYTimesBook>>()
  private val isEmptyLiveData = MutableLiveData(true)
  private val isSuccessfulLiveData = MutableLiveData(Pair(true, ""))
  
  fun getBestSellersOverview(onLoadComplete: () -> Unit = {}) {
    compositeDisposable += repository.getBestSellersOverview()
      .subscribeOn(ioScheduler)
      .observeOn(mainThreadScheduler)
      .doOnSubscribe {
        isLoadingLiveData.value = true
      }
      .doAfterTerminate {
        isLoadingLiveData.value = false
        onLoadComplete()
      }
      .subscribeBy(
        onSuccess = { onGetBestSellersOverviewSuccess(it) },
        onError = { onGetBestSellersOverviewFailed() }
      )
  }
  
  private fun onGetBestSellersOverviewSuccess(lists: List<BestSellersOverviewList>) {
    for (list in lists) {
      topBooks.addAll(list.books)
    }
    bestSellersListLiveData.value = topBooks
    isSuccessfulLiveData.value = Pair(true, "")
  }
  
  private fun onGetBestSellersOverviewFailed() {
    isSuccessfulLiveData.value = Pair(false, "")
  }
  
  fun getBestSellersListNames(onLoadComplete: () -> Unit = {}) {
    compositeDisposable += repository.getBestSellersListNames()
      .map {
        it.results
      }
      .subscribeOn(ioScheduler)
      .observeOn(mainThreadScheduler)
      .doAfterTerminate {
        onLoadComplete()
      }
      .subscribeBy(
        onSuccess = { onGetBestSellersListNamesSuccess(it) },
        onError = { onGetBestSellersListNamesFailed() }
      )
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
    val books = mutableListOf<BestSellersBook>()
    compositeDisposable += repository.getBestSellersList(listNameMap[listName].toString())
      .map {
        for (item in it.results) {
          books.add(item.bookDetails[0])
        }
      }
      .subscribeOn(ioScheduler)
      .observeOn(mainThreadScheduler)
      .doOnSubscribe {
        isLoadingLiveData.postValue(true)
      }
      .doAfterTerminate {
        isLoadingLiveData.postValue(false)
        onLoadComplete()
      }
      .subscribeBy(
        onSuccess = { onGetBestSellersListSuccess(listName, books) },
        onError = { onGetBestSellersListFailed(listName) }
      )
  }
  
  private fun onGetBestSellersListSuccess(listName: String, books: MutableList<BestSellersBook>) {
    if (books.size < 1) {
      isEmptyLiveData.postValue(true)
    } else {
      
      bestSellersListLiveData.postValue(books)
      isSuccessfulLiveData.postValue(Pair(true, listName))
    }
  }
  
  private fun onGetBestSellersListFailed(listName: String) {
    isSuccessfulLiveData.postValue( Pair(false, listName))
  }
  
  fun getMultipleLists(listNames: Set<String>, onLoadComplete: () -> Unit = {}) {
    val books = mutableListOf<BestSellersBook>()
    val observable: Observable<String> = Observable.fromIterable(listNames)
    compositeDisposable += observable
      .subscribeOn(ioScheduler)
      .observeOn(mainThreadScheduler)
      .doOnSubscribe {
        isLoadingLiveData.postValue( true)
      }
      .doOnTerminate {
        isLoadingLiveData.postValue( false)
        onLoadComplete()
      }
      .flatMap {
        repository.getBestSellersList(listNameMap[it]!!)
          .subscribeOn(ioScheduler)
          .observeOn(mainThreadScheduler)
          .toObservable()
      }
      .flatMap {
        for (item in it.results) {
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
    bestSellersListLiveData.postValue( books)
  }
  
  private fun onGetMultipleListsFailed() {
    isSuccessfulLiveData.postValue( Pair(false, ""))
  }
  
  fun getCurrentReadings() {
  
  }
  
  fun getIsLoadingLiveData(): MutableLiveData<Boolean> = isLoadingLiveData
  
  fun getListTitlesLiveData(): MutableLiveData<MutableSet<String>> = listTitlesLiveData
  
  fun getBestSellersListLiveData(): MutableLiveData<List<NYTimesBook>> = bestSellersListLiveData
  
  fun getIsEmptyLiveData(): MutableLiveData<Boolean> = isEmptyLiveData
  
  fun getIsSuccessfulLiveData(): MutableLiveData<Pair<Boolean, String>> = isSuccessfulLiveData
  
  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
