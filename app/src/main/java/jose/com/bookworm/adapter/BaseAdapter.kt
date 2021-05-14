package jose.com.bookworm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import jose.com.bookworm.R
import jose.com.bookworm.views.library.LibraryBookViewHolder

/**
 * Typed adapter extending from [RecyclerView.Adapter].
 *
 * @param layout the ID of the desired ViewHolder layout
 * @param onItemClick function to execute when an item is clicked
 * @param onItemLongClick function to execute when an item is long pressed
 *
 * "UNCHECKED_CAST" warning is suppressed because we must always specify a type when instantiating
 * this adapter.
 * */
@Suppress("UNCHECKED_CAST")
class BaseAdapter<T>(
  private val layout: Int,
  private val onItemClick: (T) -> Unit,
  private val onItemLongClick: (T) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  private var tracker: SelectionTracker<Long>? = null
  
  var data: List<T> = emptyList()
    set(value) {
      field = value
      //TODO change this to implement DiffUtils
      notifyDataSetChanged()
    }
  
  init {
    setHasStableIds(true)
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
      R.layout.best_seller_list_item -> BestSellersViewHolder(view, tracker)
      else -> CurrentReadingViewHolder(view)
    }
  }
  
  override fun getItemViewType(position: Int): Int = layout
  
  override fun onBindViewHolder(
    holder: RecyclerView.ViewHolder,
    position: Int
  ) {
    (holder as Binder<T>).bind(data[position], onItemClick, onItemLongClick)
  }
  
  override fun getItemId(position: Int): Long {
    return position.toLong()
  }
  
  override fun getItemCount() = data.size
  
  
  fun setTracker(tracker: SelectionTracker<Long>?) {
    this.tracker = tracker
  }
  
  interface Binder<T> {
    fun bind(data: T, onItemClick: (T) -> Unit = {}, onItemLongClick: (T) -> Unit = {}) {}
  }
}
