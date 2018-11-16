package jose.com.bookworm.model.nytimes

import com.google.gson.annotations.SerializedName

data class TimesBookResponse(
    @SerializedName("num_results")
    val booksCount: Int,
    val results: List<TimesBook>
)