package jose.com.bookworm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jose.com.bookworm.R

@Suppress("UNCHECKED_CAST")
class GenericAdapter<T>(
    val layout: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return getViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
            , viewType
        )
    }

    private fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder{
        return when(viewType){
            R.layout.book_list_item -> LibraryBookViewHolder(view)
            R.layout.best_seller_list_item -> BestSellersViewHolder(view)
            else -> CurrentReadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as Binder<T>).bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    interface Binder<T> {
        fun bind(data: T)
    }
}
