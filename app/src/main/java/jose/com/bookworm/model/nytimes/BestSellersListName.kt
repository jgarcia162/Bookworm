package jose.com.bookworm.model.nytimes

import com.google.gson.annotations.SerializedName

/** Name for a best-sellers list */
data class BestSellersListName(
    @SerializedName("list_name")
    val listName: String,
    @SerializedName("display_name")
    val displayName: String
)
