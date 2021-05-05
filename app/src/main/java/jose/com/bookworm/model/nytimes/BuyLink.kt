package jose.com.bookworm.model.nytimes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/** A link to purchase a book */
@Parcelize
data class BuyLink(
  val name: String,
  val url: String
) : Parcelable
