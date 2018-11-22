package jose.com.bookworm.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import jose.com.bookworm.R
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.presenters.LibraryPresenter
import javax.inject.Inject

class LibraryBookViewHolder(
  itemView: View
): RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<Book>, View.OnClickListener {
  @Inject
  lateinit var presenter: LibraryPresenter

  private lateinit var book: Book
  private val titleTV: TextView = itemView.findViewById(R.id.title_tv)
  private val authorTV: TextView = itemView.findViewById(R.id.author_tv)
  private val publishedTV: TextView = itemView.findViewById(R.id.published_tv)
  private val checkedTV: TextView = itemView.findViewById(R.id.checked_tv)
  private val categoryTV: TextView = itemView.findViewById(R.id.category_tv)
  private val bookCoverIV: ImageView = itemView.findViewById(R.id.book_cover_iv)

  override fun onClick(view: View) {
    presenter.showBookDetails(book)
  }

  override fun bind(data: Book) {
    book = data
    titleTV.text = data.title
    authorTV.text = data.author
    publishedTV.text = data.yearPublished.toString()
    checkedTV.text = if(data.isInLibrary) "" else "OUT"
    categoryTV.text = data.categories[0]
    //TODO set cover image
    itemView.setOnClickListener(this)
  }
}
