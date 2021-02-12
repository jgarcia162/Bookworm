package jose.com.bookworm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jose.com.bookworm.R
import jose.com.bookworm.views.library.LibraryBookViewHolder

/**
 * Typed adapter extending from [RecyclerView.Adapter].
 *
 * @param layout the ID of the desired ViewHolder layout
 * @param listener optional Nullable listener interface to provide callbacks in calling container.
 * */
@Suppress("UNCHECKED_CAST")
class BaseAdapter<T, U>(
  private val layout: Int,
  private val listener: U? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  
  var data: List<T> = emptyList()
    set(value) {
      field = value
      //TODO change this to implement DiffUtils
      notifyDataSetChanged()
    }
  
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RecyclerView.ViewHolder {
    return getViewHolder(
      LayoutInflater.from(parent.context)
        .inflate(viewType, parent, false), viewType
    )
  }
  
  /**
   * Returns an instance of a [RecyclerView.ViewHolder] of the type corresponding to the layout
   * specified in the constructor.
   * */
  private fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      R.layout.book_list_item -> LibraryBookViewHolder(view)
      R.layout.best_seller_list_item -> BestSellersViewHolder(view)
      else -> CurrentReadingViewHolder(view)
    }
  }
  
  override fun getItemViewType(position: Int): Int = layout
  
  override fun onBindViewHolder(
    holder: RecyclerView.ViewHolder,
    position: Int
  ) {
    if (listener !== null) {
      (holder as Binder<T, U>).bind(data[position], listener)
    } else {
      (holder as Binder<T, U>).bind(data[position])
    }
  }
  
  override fun getItemCount() = data.size
  
  interface Binder<T, U> {
    fun bind(data: T) {}
    fun bind(data: T, listener: U) {}
  }
}
