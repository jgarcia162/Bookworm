package jose.com.bookworm.model.nytimes

import com.google.gson.annotations.SerializedName

/** Represents a book from a best-seller list */
data class BestSellersBook(
    val title: String,
    val author: String,
    val description: String,
    val publisher: String,
    @SerializedName("primary_isbn10")
    val isbn10: String,
    @SerializedName("primary_isbn13")
    val isbn13: String
): NYTimesBook(title, author)