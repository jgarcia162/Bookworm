package jose.com.bookworm.model.nytimes

import com.google.gson.annotations.SerializedName

/**
 * Represents a book from a best-seller overview list. This object is returned when querying for
 * overall best sellers.
 */
data class BestSellersOverviewBook(
  @SerializedName("amazon_product_url")
  val amazonUrl: String = "",
  override val author: String,
  @SerializedName("book_image")
  override val bookImageUrl: String,
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
  override val title: String,
  @SerializedName("buy_links")
  val buyLinks: List<BuyLink>
) : NYTimesBook()