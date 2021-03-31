package jose.com.bookworm.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import jose.com.bookworm.R
import jose.com.bookworm.model.nytimes.BestSellersBook
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.model.nytimes.NYTimesBook

class BestSellersViewHolder(
  itemView: View
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), BaseAdapter.Binder<NYTimesBook, Unit> {
  private val bookImageView: ImageView = itemView.findViewById(R.id.book_image_iv)
  
  override fun bind(data: NYTimesBook) {
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
  }
}
