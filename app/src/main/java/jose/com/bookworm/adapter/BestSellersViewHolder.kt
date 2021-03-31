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
        Picasso.get()
          .load(data.bookImage)
          .resize(data.bookImageWidth, data.bookImageHeight)
          .noPlaceholder()
          .into(bookImageView)
      }
      is BestSellersOverviewBook ->
        Picasso.get()
          .load(data.bookImage)
          .resize(data.bookImageWidth, data.bookImageHeight)
          .noPlaceholder()
          .into(bookImageView)
    }
    itemView.setOnClickListener(this)
  }
  
  override fun onClick(v: View?) {
    listener.clickBook(book)
  }
}
