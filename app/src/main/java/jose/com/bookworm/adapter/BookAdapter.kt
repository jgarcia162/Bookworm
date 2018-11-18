package jose.com.bookworm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jose.com.bookworm.R
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.presenters.FeedPresenter

class BookAdapter(
    val presenter: FeedPresenter
) : RecyclerView.Adapter<BookViewHolder>() {
    var data: MutableList<Book> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

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
        data[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data.size
}
