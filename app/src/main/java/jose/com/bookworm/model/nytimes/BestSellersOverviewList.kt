package jose.com.bookworm.model.nytimes

import com.google.gson.annotations.SerializedName

/** A list containing [BestSellersOverviewBook]s */
data class BestSellersOverviewList(
    @SerializedName("list_id")
    val listId: Int,
    @SerializedName("list_name")
    val listName: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("list_image")
    val listImage: String,
    val books: List<BestSellersOverviewBook>
)