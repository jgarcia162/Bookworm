package jose.com.bookworm.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

@Suppress("UNCHECKED_CAST")
abstract class GenericAdapter<T> : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
    var data: List<T> = emptyList()
        set(value) {
            field = value
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

    abstract fun getViewHolder(view: View, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder
//        return when (viewType) {
//            R.layout.book_list_item -> LibraryBookViewHolder(view)
//            R.layout.best_seller_list_item -> BestSellersViewHolder(view)
//            else -> CurrentReadingViewHolder(view)
//        }


    override fun getItemViewType(position: Int): Int =
        getLayoutId(position, data[position])

    abstract fun getLayoutId(position: Int, obj: T): Int

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
