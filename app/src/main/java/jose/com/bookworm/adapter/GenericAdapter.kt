package jose.com.bookworm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jose.com.bookworm.R

@Suppress("UNCHECKED_CAST")
class GenericAdapter<T> (val layout: Int) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
    var data: List<T> = emptyList()
        set(value) {
            field = value
            //TODO change this to implement DiffUtils
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return getViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
            , viewType
        )
    }

    private fun getViewHolder(view: View, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.book_list_item -> LibraryBookViewHolder(view)
            R.layout.best_seller_list_item -> BestSellersViewHolder(view)
            else -> CurrentReadingViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int = layout

    override fun onBindViewHolder(
        holder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as Binder<T>).bind(data[position])
    }

    override fun getItemCount() = data.size

    interface Binder<T> {
        fun bind(data: T)
    }
}
