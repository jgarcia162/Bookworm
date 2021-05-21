package jose.com.bookworm.adapter

import android.view.View
import android.widget.ImageView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import jose.com.bookworm.R
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook

class BestSellersViewHolder(
  itemView: View,
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView),
  BaseAdapter.Binder<BestSellersOverviewBook> {
  private val bookImageView: ImageView = itemView.findViewById(R.id.iv_book_image)
  private val card: MaterialCardView = itemView.findViewById(R.id.card_best_seller)
  private lateinit var book: BestSellersOverviewBook
  
  override fun bind(
    data: BestSellersOverviewBook,
    onItemClick: (BestSellersOverviewBook) -> Unit,
    onItemLongClick: (BestSellersOverviewBook) -> Unit
  ) {
    this.book = data
    
    Picasso.get()
      .load(data.bookImageUrl)
      .resize(300, 500)
      .noFade()
      .noPlaceholder()
      .into(bookImageView)
    
    card.onClick {
      onItemClick(data)
    }
  }
}
