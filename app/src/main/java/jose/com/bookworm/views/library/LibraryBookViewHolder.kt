package jose.com.bookworm.views.library

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jose.com.bookworm.R
import jose.com.bookworm.adapter.BaseAdapter
import jose.com.bookworm.model.roommodel.Book
import timber.log.Timber

class LibraryBookViewHolder(
  itemView: View
) : RecyclerView.ViewHolder(itemView), BaseAdapter.Binder<Book, LibraryInterface>, View.OnClickListener {
  private lateinit var libraryInterface: LibraryInterface
  private lateinit var book: Book
  private val titleTV: TextView = itemView.findViewById(R.id.title_tv)
  private val authorTV: TextView = itemView.findViewById(R.id.author_tv)
  private val publishedTV: TextView = itemView.findViewById(R.id.published_tv)
  private val checkedTV: TextView = itemView.findViewById(R.id.checked_tv)
  private val categoryTV: TextView = itemView.findViewById(R.id.category_tv)
  private val bookCoverIV: ImageView = itemView.findViewById(R.id.book_cover_iv)
  
  override fun onClick(view: View) {
    libraryInterface.showBookDetails(book)
  }
  
  override fun bind(data: Book, listener: LibraryInterface) {
    libraryInterface = listener
    book = data
    Timber.d("BOOK $book")
    titleTV.text = data.title
    authorTV.text = data.author
    publishedTV.text = data.yearPublished.toString()
    checkedTV.text = if (data.isInLibrary) "IN" else "OUT"
    categoryTV.text = data.categories
    //TODO set cover image
    Picasso
      .get()
      .load("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi1.wp.com%2Fwww.tor.com%2Fwp-content%2Fuploads%2F2019%2F07%2FDune-cover-1.jpg%3Ftype%3Dvertical%26ssl%3D1&f=1&nofb=1")
      .centerCrop()
      .resize(150, 200)
      .into(bookCoverIV)
    itemView.setOnClickListener(this)
  }
}
