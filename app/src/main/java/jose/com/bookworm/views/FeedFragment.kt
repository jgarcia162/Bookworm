package jose.com.bookworm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.adapter.BaseAdapter
import jose.com.bookworm.databinding.FragmentFeedBinding
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.extensions.toast
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.viewmodel.AddBookViewModel
import jose.com.bookworm.viewmodel.FeedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
  
    binding.bestSellersRv.layoutManager = GridLayoutManager(
      context,
      3,
      GridLayoutManager.VERTICAL,
      false
    )
  
    binding.bestSellersRv.adapter = bestSellersAdapter
  
    setFilterClick()
  
    observeLiveData()
  }
  
  private fun observeLiveData() {
    feedViewModel.getIsLoadingLiveData().observe(viewLifecycleOwner, { isLoading ->
      run {
        if (isLoading) {
          showLoading()
        } else {
          hideLoading()
        }
      }
    })
  
    feedViewModel.getListTitlesLiveData()
      .observe(viewLifecycleOwner, { loadListNamesChips(it) })
  
    feedViewModel.getBestSellersListLiveData()
      .observe(viewLifecycleOwner, { showBestSellersList(it) })
  
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
  
  override fun onStart() {
    super.onStart()
  
    CoroutineScope(Dispatchers.Main).launch {
      feedViewModel.getBestSellersListNames()
      feedViewModel.getBestSellersOverview()
    }
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
    activity?.toast(getString(R.string.best_sellers_failed))
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
  
  fun showBestSellersListFailed(listName: String) {
    activity?.toast("Couldn't load books for list $listName")
  }
  
  override fun onDestroyView() {
    super.onDestroyView()
    fragmentFeedBinding = null
  }
}
