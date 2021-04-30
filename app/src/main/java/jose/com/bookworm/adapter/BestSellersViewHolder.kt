package jose.com.bookworm.adapter

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jose.com.bookworm.R
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.extensions.onLongClick
import jose.com.bookworm.model.nytimes.BestSellersBook
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.model.nytimes.NYTimesBook

class BestSellersViewHolder(
  itemView: View
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView),
  BaseAdapter.Binder<NYTimesBook> {
  private val bookImageView: ImageView = itemView.findViewById(R.id.book_image_iv)
  private lateinit var book: NYTimesBook
  
  override fun bind(
    data: NYTimesBook,
    onItemClick: (NYTimesBook) -> Unit,
    onItemLongClick: (NYTimesBook) -> Unit
  ) {
    this.book = data
    //after a filter is applied to the list of best sellers the book item will change from
    // BestSellersOverviewBook to BestSellersBook so we use the when statement to handle both cases
    // even though the fields are the same
    when (data) {
      is BestSellersBook -> {
        loadImageIntoImageView(data.bookImageWidth, data.bookImageHeight, data.bookImageUrl)
      }
      is BestSellersOverviewBook -> {
        loadImageIntoImageView(data.bookImageWidth, data.bookImageHeight, data.bookImageUrl)
      }
    }
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
    val imageWidth = pair.second
    val imageHeight = pair.first
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
