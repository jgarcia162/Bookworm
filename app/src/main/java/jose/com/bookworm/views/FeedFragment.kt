package jose.com.bookworm.views

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.adapter.BaseAdapter
import jose.com.bookworm.adapter.DetailsLookup
import jose.com.bookworm.databinding.FragmentFeedBinding
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.extensions.snackbar
import jose.com.bookworm.extensions.toast
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.viewmodel.AddBookViewModel
import jose.com.bookworm.viewmodel.FeedViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment @Inject constructor() : Fragment(), ChipsDialogFragment.ChipsListener {
  private lateinit var bestSellersAdapter: BaseAdapter<BestSellersOverviewBook>
  private lateinit var categoryTitles: Set<String>
  private var fragmentFeedBinding: FragmentFeedBinding? = null
  private val binding get() = fragmentFeedBinding!!
  
  private val feedViewModel: FeedViewModel by viewModels()
  private val addBookViewModel: AddBookViewModel by viewModels()
  
  private var tracker: SelectionTracker<Long>? = null
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState != null)
      tracker?.onRestoreInstanceState(savedInstanceState)
  }
  
  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    
    tracker?.onSaveInstanceState(outState)
  }
  
  override fun onStart() {
    super.onStart()
    viewLifecycleOwner.lifecycleScope.launch {
      feedViewModel.getBestSellersListNames(onLoadFailed = { showBestSellersListFailed(it) })
      feedViewModel.getBestSellersOverview(onLoadFailed = { showBestSellersListFailed(it) })
    }
  }
  
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    fragmentFeedBinding = FragmentFeedBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    bestSellersAdapter = BaseAdapter(
      R.layout.best_seller_list_item,
      onItemClick = { onItemClick(it) },
      onItemLongClick = { onItemLongClick(it) }
    )
    
    binding.apply {
      bestSellersRv.apply {
        layoutManager = GridLayoutManager(
          context,
          3,
          GridLayoutManager.VERTICAL,
          false
        )
        adapter = bestSellersAdapter
  
        tracker = SelectionTracker.Builder(
          "selection-1",
          bestSellersRv,
          StableIdKeyProvider(bestSellersRv),
          DetailsLookup(bestSellersRv),
          StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
          SelectionPredicates.createSelectAnything()
        ).build()
  
        bestSellersAdapter.setTracker(tracker)
      }
    }
  
    tracker?.addObserver(
      object : SelectionTracker.SelectionObserver<Long>() {
        override fun onSelectionChanged() {
          val nItems: Int? = tracker?.selection?.size()
          if (nItems != null && nItems > 0) {
          
            context?.toast("$nItems")
            // Change title and color of action bar
            activity?.title = "$nItems items selected"
            activity?.actionBar?.setBackgroundDrawable(
              ColorDrawable(Color.parseColor("#ef6c00"))
            )
          } else {
          
            // Reset color and title to default values
          
            activity?.title = "RVSelection"
            activity?.actionBar?.setBackgroundDrawable(
              ColorDrawable(getColor(context!!, R.color.colorAccent))
            )
          }
          // More code here
        }
      })
  
    setFilterClick()
  
    observeLiveData()
  }
  
  private fun observeLiveData() {
    feedViewModel.getIsLoadingLiveData().observe(viewLifecycleOwner, { toggleLoadIndicator(it) })
  
    feedViewModel.listTitles.observe(viewLifecycleOwner, { loadListNamesChips(it) })
  
    feedViewModel.bestSellersList.observe(viewLifecycleOwner, { showBestSellersList(it) })
  
    feedViewModel.getIsEmptyLiveData().observe(viewLifecycleOwner, { showEmptyState(it) })
  
    feedViewModel.getIsSuccessfulLiveData()
      .observe(viewLifecycleOwner, { (isSuccessful, listName) ->
        if (isSuccessful)
          showGetBestSellersSuccess(listName)
        else {
          showGetBestSellersFailed()
        }
      })
  }
  
  private fun setFilterClick() {
    binding.filterIcon.onClick {
      //TODO re-inflate fragment if created
      ChipsDialogFragment()
        .apply {
          listener = this@FeedFragment
          chipTitles = categoryTitles
        }
        .show(parentFragmentManager, ChipsDialogFragment::class.simpleName)
    }
  }
  
  private fun showBestSellersListFailed(throwable: Throwable) {
    binding.root.snackbar("Slow down there cowboy!")
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
  
  fun toggleLoadIndicator(loading: Boolean) {
    if (loading) showLoading()
    else hideLoading()
  }
  
  fun showLoading() {
  
  }
  
  fun hideLoading() {
  
  }
  
  private fun loadListNamesChips(listTitles: MutableSet<String>) {
    categoryTitles = listTitles
  }
  
  private fun onItemLongClick(book: BestSellersOverviewBook) {
    addBookViewModel.addBook(
      title = book.title,
      author = book.author,
      imageUrl = book.bookImageUrl,
      onAddBookComplete = { context?.toast("Book Added") },
      onAddBookError = { context?.toast("Error") }
    )
  }
  
  private fun onItemClick(book: BestSellersOverviewBook) {
    val bundle = Bundle()
    //TODO create constant for book key
    bundle.putParcelable("book", book)
    
    findNavController().navigate(R.id.action_feedFragment_to_bookDetailsFragment, bundle)
  }
  
  private fun showGetBestSellersSuccess(listName: String) {
  }
  
  private fun showGetBestSellersFailed() {
//    activity?.toast(getString(R.string.best_sellers_failed))
  }
  
  private fun showBestSellersList(books: List<BestSellersOverviewBook>) {
    bestSellersAdapter.data = books
  }
  
  fun getBestSellerList(listName: String) {
    feedViewModel.getBestSellersList(listName)
  }
  
  override suspend fun getOverviewList() {
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
  
  override fun onDestroyView() {
    super.onDestroyView()
    fragmentFeedBinding = null
  }
}
