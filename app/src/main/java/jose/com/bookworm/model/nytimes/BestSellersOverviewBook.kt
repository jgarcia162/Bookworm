package jose.com.bookworm.model.nytimes

import com.google.gson.annotations.SerializedName

/**
 * Represents a book from a best-seller overview list
 */
data class BestSellersOverviewBook(
    @SerializedName("amazon_product_url")
    val amazonUrl: String = "",
    override val author: String,
    @SerializedName("book_image")
    val bookImage: String,
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