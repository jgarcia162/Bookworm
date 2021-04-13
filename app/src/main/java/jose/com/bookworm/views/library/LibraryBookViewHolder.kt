package jose.com.bookworm.views.library

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jose.com.bookworm.R
import jose.com.bookworm.adapter.BaseAdapter
import jose.com.bookworm.model.roommodel.Book

class LibraryBookViewHolder(
  itemView: View
) : RecyclerView.ViewHolder(itemView), BaseAdapter.Binder<Book> {
  private lateinit var book: Book
  private val titleTV: TextView = itemView.findViewById(R.id.title_tv)
  private val authorTV: TextView = itemView.findViewById(R.id.author_tv)
  private val publishedTV: TextView = itemView.findViewById(R.id.published_tv)
  private val checkedTV: TextView = itemView.findViewById(R.id.checked_tv)
  private val categoryTV: TextView = itemView.findViewById(R.id.category_tv)
  private val bookCoverIV: ImageView = itemView.findViewById(R.id.book_cover_iv)
  
  override fun bind(data: Book, onItemClick: (Book) -> Unit, onItemLongClick: (Book) -> Unit) {
    book = data
    titleTV.text = data.title
    authorTV.text = data.author
    publishedTV.text = data.yearPublished.toString()
    
    if (data.isInLibrary) {
      checkedTV.text = itemView.context.getString(R.string.book_in)
      checkedTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
    } else {
      checkedTV.text = itemView.context.getString(R.string.book_out)
      checkedTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
    }
    
    categoryTV.text = data.categories
    Picasso
      .get()
      .load(data.coverUrl)
      .centerCrop()
      .resize(150, 200)
      .placeholder(R.drawable.placeholder_books)
      .into(bookCoverIV)
    
    itemView.setOnClickListener { onItemClick(book) }
    itemView.setOnLongClickListener {
      onItemLongClick(book)
      return@setOnLongClickListener true
    }
  }
}
