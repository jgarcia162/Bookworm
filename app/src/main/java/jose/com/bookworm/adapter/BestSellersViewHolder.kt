package jose.com.bookworm.adapter

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jose.com.bookworm.R
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.extensions.onLongClick
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook

class BestSellersViewHolder(
  itemView: View
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
    
    itemView.onLongClick {
      onItemLongClick(data)
      return@onLongClick true
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
}
