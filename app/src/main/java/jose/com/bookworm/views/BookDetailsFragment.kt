package jose.com.bookworm.views

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment
import jose.com.bookworm.R
import jose.com.bookworm.di.Injector
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.BookDetailsViewModel
import javax.inject.Inject

class BookDetailsFragment : DaggerFragment() {
  
  @Inject
  lateinit var bookDetailsViewModel: BookDetailsViewModel
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    Injector.applicationComponent.inject(this)
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

  fun showBookCheckedOut() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun showBookCheckedIn() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun showEditBookLayout() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun hideEditBookLayout() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun showBookDetails() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun updateBookData(book: Book) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun updateProgressSeekBar() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.details_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.details_edit_book_menu_option -> showEditBookLayout()
    }
    return true
  }
}