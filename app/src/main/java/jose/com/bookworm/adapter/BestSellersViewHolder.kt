package jose.com.bookworm.adapter

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jose.com.bookworm.R
import jose.com.bookworm.model.nytimes.BestSellersBook
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.model.nytimes.NYTimesBook

class BestSellersViewHolder(
  itemView: View
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView),
  BaseAdapter.Binder<NYTimesBook, FeedInterface>,
  View.OnClickListener {
  private val bookImageView: ImageView = itemView.findViewById(R.id.book_image_iv)
  private lateinit var listener: FeedInterface
  private lateinit var book: NYTimesBook
  
  override fun bind(data: NYTimesBook, listener: FeedInterface) {
    this.listener = listener
    this.book = data
    when (data) {
      is BestSellersBook -> {
        loadImageIntoImageView(data.bookImageWidth, data.bookImageHeight, data.bookImage)
      }
      is BestSellersOverviewBook -> {
        loadImageIntoImageView(data.bookImageWidth, data.bookImageHeight, data.bookImage)
      }
    }
    itemView.setOnClickListener(this)
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
  
  override fun onClick(v: View?) {
    listener.clickBook(book)
  }
}
