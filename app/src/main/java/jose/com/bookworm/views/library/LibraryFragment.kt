package jose.com.bookworm.views.library

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.adapter.BaseAdapter
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.LibraryViewModel
import kotlinx.android.synthetic.main.fragment_library.*
import javax.inject.Inject

@AndroidEntryPoint
class LibraryFragment @Inject constructor() : Fragment(), LibraryInterface, LifecycleOwner {
  private lateinit var adapter: BaseAdapter<Book, LibraryInterface>
  
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
  ): View? {
    return inflater.inflate(R.layout.fragment_library, container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter = BaseAdapter(R.layout.book_list_item, this)
  
    library_rv.setHasFixedSize(false)
    library_rv.adapter = adapter
    
    observeLiveData()
  }
  
  private fun observeLiveData() {
    libraryViewModel.getBooks().observe(viewLifecycleOwner, { books ->
      adapter.data = books
    })
  
    libraryViewModel.getIsLoading().observe(viewLifecycleOwner, { hideLoading() })
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
//      activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_fragment_container, LibraryFragment())?.commit()
  }
  
}
