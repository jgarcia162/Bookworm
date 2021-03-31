package jose.com.bookworm.model.nytimes

import com.google.gson.annotations.SerializedName

/** Represents a book from a best-seller list */
data class BestSellersBook(
  override val title: String,
  override val author: String,
  @SerializedName("book_image")
  val bookImage: String,
  @SerializedName("book_image_width")
  val bookImageWidth: Int,
  @SerializedName("book_image_height")
  val bookImageHeight: Int,
  val description: String,
  val publisher: String,
  @SerializedName("primary_isbn10")
  val isbn10: String,
  @SerializedName("primary_isbn13")
  val isbn13: String
): NYTimesBook()