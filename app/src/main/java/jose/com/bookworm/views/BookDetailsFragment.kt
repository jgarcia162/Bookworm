package jose.com.bookworm.views

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.DoubleClickListener
import jose.com.bookworm.R
import jose.com.bookworm.databinding.FragmentBookDetailsBinding
import jose.com.bookworm.extensions.toast
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.AddBookViewModel

@AndroidEntryPoint
class BookDetailsFragment : Fragment() {
  private val addBookViewModel: AddBookViewModel by viewModels()
  private var fragmentBookDetailsBinding: FragmentBookDetailsBinding? = null
  private val binding get() = fragmentBookDetailsBinding!!
  private var bookDetails: BookDetails? = null
  
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
    val book: Any? = arguments?.getParcelable("book")
    bookDetails = when (book) {
      is BestSellersOverviewBook -> BookDetails(
        book.title,
        book.author,
        book.bookImageUrl,
        book.description,
        book.category
      )
      is Book -> BookDetails(
        book.title,
        book.author,
        book.coverUrl,
        book.description,
        book.categories
      )
      else -> null
    }
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    bookDetails?.let {
      binding.apply {
        Picasso.get().load(it.bookImageUrl).into(ivDetailsBookCover)
        ivDetailsBookCover.setOnClickListener(object : DoubleClickListener() {
          override fun onDoubleClick(v: View?) {
            addBookToLibrary(it)
          }
        })
        tvDetailsTitle.text = it.title
        tvDetailsAuthor.text = it.author
        tvDetailsCategory.text = it.category
        tvDetailsDescription.text = it.description
      }
    }
  }
  
  private fun addBookToLibrary(it: BookDetails) {
    addBookViewModel.addBook(
      title = it.title,
      author = it.author,
      description = it.description,
      imageUrl = it.bookImageUrl,
      onAddBookComplete = { context?.toast("Book Added") },
      onAddBookError = { context?.toast("Error") }
    )
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
  
  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.details_menu, menu)
  }
  
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.details_edit_book_menu_option -> showEditBookLayout()
    }
    return true
  }
  
  internal data class BookDetails(
    val title: String,
    val author: String,
    val bookImageUrl: String?,
    val description: String,
    val category: String,
  )
}