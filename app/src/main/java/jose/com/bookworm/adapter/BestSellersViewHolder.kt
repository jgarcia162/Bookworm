package jose.com.bookworm.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jose.com.bookworm.R
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook


class BestSellersViewHolder(
  itemView: View,
  private val tracker: SelectionTracker<Long>?
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView),
  BaseAdapter.Binder<BestSellersOverviewBook> {
  private val bookImageView: ImageView = itemView.findViewById(R.id.book_image_iv)
  private lateinit var book: BestSellersOverviewBook
  
  override fun bind(
    data: BestSellersOverviewBook,
    onItemClick: (BestSellersOverviewBook) -> Unit,
    onItemLongClick: (BestSellersOverviewBook) -> Unit
  ) {
    this.book = data
  
    loadImageIntoImageView(data.bookImageWidth, data.bookImageHeight, data.bookImageUrl)
  
    itemView.onClick {
      onItemClick(data)
    }
//
//    itemView.onLongClick {
//      onItemLongClick(data)
//      return@onLongClick true
//    }
  
    if (tracker!!.isSelected(adapterPosition.toLong())) {
      itemView.background = ColorDrawable(
        Color.parseColor("#80deea")
      )
    } else {
      // Reset color to white if not selected
      itemView.background = ColorDrawable(Color.WHITE)
    }
  }
  
  private fun loadImageIntoImageView(width: Int, height: Int, bookImage: String) {
    val pair = getImageDimens(width, height)
    val imageHeight = pair.first
    val imageWidth = pair.second
    Picasso.get()
      .load(bookImage)
      .resize(imageWidth, imageHeight)
      .noPlaceholder()
      .into(bookImageView)
  }
  
  private fun getImageDimens(width: Int, height: Int): Pair<Int, Int> {
    var width1 = 300
    var height1 = 500
  
    if (width >= 300) {
      width1 = width
    }
  
    if (height >= 500) {
      height1 = height
    }
  
    return Pair(height1, width1)
  }
  
  fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long?> =
    object : ItemDetailsLookup.ItemDetails<Long?>() {
      override fun getPosition(): Int {
        return adapterPosition
      }
      
      override fun getSelectionKey(): Long {
        return itemId
      }
    }
  
}

internal class DetailsLookup(private val mRecyclerView: RecyclerView) :
  ItemDetailsLookup<Long?>() {
  @Nullable
  override fun getItemDetails(@NonNull e: MotionEvent): ItemDetails<Long?>? {
    val view = mRecyclerView.findChildViewUnder(e.x, e.y)
    if (view != null) {
      val holder = mRecyclerView.getChildViewHolder(view)
      if (holder is BestSellersViewHolder) {
        return holder.getItemDetails()
      }
    }
    return null
  }
}
