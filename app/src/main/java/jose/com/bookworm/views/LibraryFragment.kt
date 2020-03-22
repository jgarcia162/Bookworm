package jose.com.bookworm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import jose.com.bookworm.R
import jose.com.bookworm.adapter.GenericAdapter
import jose.com.bookworm.di.Injector
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.LibraryViewModel
import javax.inject.Inject

class LibraryFragment : DaggerFragment() {
  @Inject
  lateinit var libraryViewModel: LibraryViewModel
  private lateinit var adapter: GenericAdapter<Book>

  override fun onCreate(savedInstanceState: Bundle?) {
    Injector.applicationComponent.inject(this)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
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
