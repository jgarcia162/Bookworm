package jose.com.bookworm.model.nytimes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Represents a book from a best-seller overview list. This object is returned when querying for
 * overall best sellers.
 */
@Parcelize
data class BestSellersOverviewBook(
  @SerializedName("amazon_product_url")
  val amazonUrl: String = "",
  val author: String,
  @SerializedName("book_image")
  val bookImageUrl: String,
  @SerializedName("book_image_width")
  val bookImageWidth: Int,
  @SerializedName("book_image_height")
  val bookImageHeight: Int,
  val description: String,
  @SerializedName("primary_isbn10")
  val isbn10: String = "",
  @SerializedName("primary_isbn13")
  val isbn13: String = "",
  val publisher: String,
  val rank: Int,
  val title: String,
  @SerializedName("buy_links")
  val buyLinks: List<BuyLink>
) : Parcelable