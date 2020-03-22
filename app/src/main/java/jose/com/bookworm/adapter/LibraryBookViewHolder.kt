package jose.com.bookworm.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jose.com.bookworm.R
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.LibraryViewModel
import javax.inject.Inject

class LibraryBookViewHolder(
  itemView: View
) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<Book>, View.OnClickListener {
  
  @Inject
  lateinit var libraryViewModel: LibraryViewModel
  
  private lateinit var book: Book
  private val titleTV: TextView = itemView.findViewById(R.id.title_tv)
  private val authorTV: TextView = itemView.findViewById(R.id.author_tv)
  private val publishedTV: TextView = itemView.findViewById(R.id.published_tv)
  private val checkedTV: TextView = itemView.findViewById(R.id.checked_tv)
  private val categoryTV: TextView = itemView.findViewById(R.id.category_tv)
  private val bookCoverIV: ImageView = itemView.findViewById(R.id.book_cover_iv)
  
  override fun onClick(view: View) {
    libraryViewModel.showBookDetails(book)
  }
  
  override fun bind(data: Book) {
    book = data
    titleTV.text = data.title
    authorTV.text = data.author
    publishedTV.text = data.yearPublished.toString()
    checkedTV.text = if (data.isInLibrary) "" else "OUT"
    categoryTV.text = data.categories[0].toString()
    //TODO set cover image
    itemView.setOnClickListener(this)
  }
}
