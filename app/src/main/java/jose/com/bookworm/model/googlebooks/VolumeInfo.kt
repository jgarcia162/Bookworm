package jose.com.bookworm.model.googlebooks

import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val publishedDate: String,
    val description: String,
    @SerializedName("industryIdentifiers")
    val identifiers: List<VolumeISBN>,
    val pageCount: Int,
    val categories: List<String>,
    @SerializedName("imageLinks")
    val images: List<VolumeImageLinks>,
    val language: String
)
