package jose.com.bookworm.views

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.databinding.FragmentBookDetailsBinding
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.BookDetailsViewModel

@AndroidEntryPoint
class BookDetailsFragment : Fragment() {
  
  private val bookDetailsViewModel: BookDetailsViewModel by viewModels()
  private var fragmentBookDetailsBinding: FragmentBookDetailsBinding? = null
  private val binding get() = fragmentBookDetailsBinding!!
  private var book: BestSellersOverviewBook? = null
  
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    fragmentBookDetailsBinding = FragmentBookDetailsBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onAttach(context: Context) {
    super.onAttach(context)
    book = arguments?.getParcelable("book")
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    book?.let {
      Picasso.get().load(it.bookImageUrl).into(binding.ivDetailsBookCover)
      binding.tvDetailsTitle.text = it.title
      binding.tvDetailsAuthor.text = it.author
      binding.tvDetailsIsbn.text = it.isbn13
      binding.tvDetailsPublished.text = it.publisher
    }
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