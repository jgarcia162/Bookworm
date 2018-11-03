package jose.com.bookworm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jose.com.bookworm.R

import jose.com.bookworm.model.Book

class BookAdapter(private var data: List<Book>?) : RecyclerView.Adapter<BookViewHolder>() {
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
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    fun setData(data: List<Book>) {
        this.data = data
    }
}
