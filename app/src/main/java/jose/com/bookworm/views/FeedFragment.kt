package jose.com.bookworm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.adapter.GenericAdapter
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.extensions.toast
import jose.com.bookworm.model.nytimes.NYTimesBook
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

@AndroidEntryPoint
class FeedFragment : Fragment(), View.OnClickListener, ChipsDialogFragment.ChipsListener {
  private lateinit var bestSellersAdapter: GenericAdapter<NYTimesBook>
  private lateinit var currentReadingAdapter: GenericAdapter<Book>
  private lateinit var categoryTitles: Set<String>
  
  private val feedViewModel: FeedViewModel by viewModels()
  
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_feed, container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    bestSellersAdapter = GenericAdapter(R.layout.best_seller_list_item)
    
    best_sellers_rv.setHasFixedSize(true)
    best_sellers_rv.adapter = bestSellersAdapter
    
    currentReadingAdapter = GenericAdapter(R.layout.current_reading_list_item)
    
    current_reading_rv.adapter = currentReadingAdapter
    
    setFilterClick()
    
    observeLiveData()
  }
  
  private fun observeLiveData() {
    feedViewModel.getIsLoadingLiveData().observe(viewLifecycleOwner, Observer<Boolean> { isLoading ->
      run {
        if (isLoading) {
          showLoading()
        } else {
          hideLoading()
        }
      }
    })
    
    feedViewModel.getListTitlesLiveData()
      .observe(viewLifecycleOwner, Observer { loadListNamesChips(it) })
    
    feedViewModel.getBestSellersListLiveData()
      .observe(viewLifecycleOwner, Observer { showBestSellersList(it) })
    
    feedViewModel.getIsEmptyLiveData().observe(viewLifecycleOwner, Observer { showEmptyState(it) })
    
    feedViewModel.getIsSuccessfulLiveData()
      .observe(viewLifecycleOwner, Observer { (isSuccessful, listName) ->
        if (isSuccessful)
          showGetBestSellersSuccess(listName)
        else {
          showGetBestSellersFailed()
        }
      })
  }
  
  private fun setFilterClick() {
    filter_icon.onClick {
      //TODO re-inflate fragment if created
      ChipsDialogFragment()
        .apply {
          listener = this@FeedFragment
          chipTitles = categoryTitles
        }
        .show(childFragmentManager.beginTransaction(), "categories_fragment")
    }
  }
  
  override fun onStart() {
    super.onStart()
    
    feedViewModel.getBestSellersListNames()
    feedViewModel.getBestSellersOverview()
    feedViewModel.getCurrentReadings()
  }
  
  fun showNotReadingAnyBooksText() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  fun hideNotReadingAnyBooksText() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  fun showRefreshing() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  fun hideRefreshing() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  fun showLoading() {
  
  }
  
  fun hideLoading() {
  
  }
  
  private fun loadListNamesChips(listTitles: MutableSet<String>) {
    categoryTitles = listTitles
  }
  
  override fun onClick(v: View?) {
    val chip = v as Chip
    feedViewModel.getBestSellersList(chip.text!!.toString())
  }
  
  private fun showGetBestSellersSuccess(listName: String) {
    suggestions_tv.text = getString(R.string.best_sellers, listName)
  }
  
  private fun showGetBestSellersFailed() {
    activity?.toast(getString(R.string.best_sellers_failed))
    suggestions_tv.text = getString(R.string.best_sellers_failed)
  }
  
  private fun showBestSellersList(books: List<NYTimesBook>) {
    bestSellersAdapter.data = books
  }
  
  fun getBestSellerList(listName: String) {
    feedViewModel.getBestSellersList(listName)
  }
  
  override fun getOverviewList() {
    feedViewModel.getBestSellersOverview()
  }
  
  override fun getMultipleLists(selectedLists: MutableSet<String>) {
    feedViewModel.getMultipleLists(selectedLists)
  }
  
  fun showEmptyState(show: Boolean) {
    //TODO hide/show empty state views
    if (show) {
      //show view
    } else {
      //hide view
    }
  }
  
  fun showBestSellersListFailed(listName: String) {
    activity?.toast("Couldn't load books for list $listName")
  }
}
