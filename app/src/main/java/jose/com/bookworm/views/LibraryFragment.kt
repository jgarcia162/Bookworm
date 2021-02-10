package jose.com.bookworm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.adapter.GenericAdapter
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.LibraryViewModel
import kotlinx.android.synthetic.main.fragment_library.*
import javax.inject.Inject

@AndroidEntryPoint
class LibraryFragment @Inject constructor() : Fragment() {
  private lateinit var adapter: GenericAdapter<Book>
  
  private val libraryViewModel: LibraryViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_library, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter = GenericAdapter(R.layout.best_seller_list_item)
  
    library_rv.setHasFixedSize(true)
    library_rv.adapter = adapter
    
    observeLiveData()
    libraryViewModel.getAllBooks()
  }
  
  private fun observeLiveData() {
    libraryViewModel.getBooks().observe(viewLifecycleOwner,{ books ->
      adapter.data = books
    })
  }

  override fun onStart() {
    super.onStart()
  }

  override fun onStop() {
    super.onStop()
  }

  fun showBookDeleted() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun showLoading() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun hideLoading() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun showEmptyLibrary() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun hideEmptyLibrary() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun showSortedBooks() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun showBookDetails(book: Book) {
    activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_fragment_container, LibraryFragment())?.commit()
  }
}
