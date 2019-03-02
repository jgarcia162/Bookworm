package jose.com.bookworm.model.googlebooks

data class VolumesResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Volume>
)

