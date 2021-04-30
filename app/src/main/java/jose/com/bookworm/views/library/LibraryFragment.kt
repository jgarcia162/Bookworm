package jose.com.bookworm.views.library

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.adapter.BaseAdapter
import jose.com.bookworm.databinding.FragmentLibraryBinding
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.extensions.toast
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.LibraryViewModel
import jose.com.bookworm.views.AddBookDialogFragment
import javax.inject.Inject

@AndroidEntryPoint
class LibraryFragment @Inject constructor() : Fragment(), LifecycleOwner, LibraryInterface, AddBookDialogFragment.AddBookInterface {
  private lateinit var adapter: BaseAdapter<Book>
  private lateinit var libraryBinding: FragmentLibraryBinding
  private val binding get() = libraryBinding!!
  
  private val libraryViewModel: LibraryViewModel by viewModels()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }
  
  override fun onPrepareOptionsMenu(menu: Menu) {
    //hide menu options as we don't need them here
    menu.clear()
    super.onPrepareOptionsMenu(menu)
  }
  
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    libraryBinding = FragmentLibraryBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter = BaseAdapter(
      R.layout.book_list_item,
      onItemClick = { showBookDetails(it) },
      onItemLongClick = { saveToLibrary(it) }
    )
  
    binding.libraryRv.setHasFixedSize(false)
    binding.libraryRv.adapter = adapter
  
    binding.addBookFab.onClick {
      showAddBookFragment()
    }
  
    observeLiveData()
  }
  
  private fun observeLiveData() {
    libraryViewModel.getBooks().observe(viewLifecycleOwner, { books ->
      adapter.data = books
    })
    
    libraryViewModel.getIsLoading().observe(viewLifecycleOwner, { hideLoading() })
  }
  
  private fun showAddBookFragment() {
    AddBookDialogFragment(this).show(childFragmentManager, AddBookDialogFragment::class.simpleName)
  }
  
  override fun onStart() {
    super.onStart()
    libraryViewModel.getAllBooks()
  }
  
  override fun showBookDeleted() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. }
  }
  
  override fun showLoading() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override fun hideLoading() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override fun showEmptyLibrary() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override fun hideEmptyLibrary() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override fun showSortedBooks() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  
  override fun showBookDetails(book: Book) {
    //TODO navigate to details screen
  }
  
  override fun saveToLibrary(book: Book) {
    context?.toast(book.title)
//    addBookViewModel.addBook(book, { onAddBookComplete() }, { onAddBookError() })
  }
  
  override fun onAddBookComplete() {
    libraryViewModel.getAllBooks {
      activity?.toast(getString(R.string.msg_book_added))
    }
  }
  
  override fun onAddBookError() {
    activity?.toast(getString(R.string.error_adding_book))
  }
  
}
