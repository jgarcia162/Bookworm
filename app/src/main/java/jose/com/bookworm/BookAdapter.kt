package jose.com.bookworm

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import jose.com.bookworm.model.Book
import jose.com.bookworm.view.BookViewHolder

/**
 * Created by Joe on 10/31/17.
 */

class BookAdapter(private var data: List<Book>?)//        Box<Book> bookBox = MyObjectBox.builder().androidContext(App.class).build().boxFor(Book.class);
  : RecyclerView.Adapter<BookViewHolder>() {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BookViewHolder {
    return BookViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.book_card_layout, parent, false)
    )
  }

  override fun onBindViewHolder(
    holder: BookViewHolder,
    position: Int
  ) {
    //        holder.bind(data.get(position));
    holder.bind(position.toString() + "")
  }

  override fun getItemCount(): Int {
    return 20
  }

  fun setData(data: List<Book>) {
    this.data = data
  }
}
