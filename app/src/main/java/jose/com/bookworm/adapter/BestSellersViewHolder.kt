package jose.com.bookworm.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import jose.com.bookworm.R
import jose.com.bookworm.model.nytimes.NYTimesBook

class BestSellersViewHolder(
    itemView: View
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<NYTimesBook>, View.OnClickListener  {
    private val titleTV: TextView = itemView.findViewById(R.id.seller_title_tv)
    private val authorTV: TextView = itemView.findViewById(R.id.seller_author_tv)

    override fun bind(data: NYTimesBook) {
        titleTV.text = data.title
        authorTV.text = data.author
    }

    override fun onClick(p0: View?) {

    }
}
