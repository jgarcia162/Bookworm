package jose.com.bookworm.model.nytimes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/** Represents a book from a best-seller list. This object is returned when querying for a specific
 * list */
@Parcelize
data class BestSellersBook(
  val title: String,
  val author: String,
  @SerializedName("book_image")
  val bookImageUrl: String,
  @SerializedName("book_image_width")
  val bookImageWidth: Int,
  @SerializedName("book_image_height")
  val bookImageHeight: Int,
  val description: String,
  val publisher: String,
  @SerializedName("primary_isbn10")
  val isbn10: String,
  @SerializedName("primary_isbn13")
  val isbn13: String,
  val category: String?
) : Parcelable