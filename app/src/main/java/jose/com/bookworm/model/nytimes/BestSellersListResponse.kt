package jose.com.bookworm.model.nytimes

import com.google.gson.annotations.SerializedName

/** Response from a request for a [BestSellersListItem] */
data class BestSellersListResponse(
    @SerializedName("num_results")
    val numResults: Int,
    val results: List<BestSellersListItem>
)
