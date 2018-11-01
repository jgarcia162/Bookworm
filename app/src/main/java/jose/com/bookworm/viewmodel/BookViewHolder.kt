package jose.com.bookworm.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import jose.com.bookworm.R
import jose.com.bookworm.model.Book

/**
 * Created by Joe on 10/30/17.
 */

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  private val textView: TextView

  init {
    textView = itemView.findViewById(R.id.title_text_view)
  }

  fun bind(book: Book) {
    textView.text = book.title
  }

  fun bind(string: String) {
    textView.text = string
  }
}

