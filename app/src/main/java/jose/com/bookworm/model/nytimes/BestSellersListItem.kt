package jose.com.bookworm.model.nytimes

import com.google.gson.annotations.SerializedName

/**
 * Best-sellers list-item of [BestSellersBook]
 *
 * @field bookDetails is a single book
 * @field amazonUrl is a link to the book
 *
 * */
data class BestSellersListItem(
    @SerializedName("list_name")
    val listName: String,
    @SerializedName("display_name")
    val displayName: String,
    val rank: Int,
    @SerializedName("amazon_product_url")
    val amazonUrl: String,
    @SerializedName("book_details")
    val listOfBooks: List<BestSellersBook>
)
