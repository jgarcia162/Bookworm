package jose.com.bookworm.model

data class VolumesResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Volume>
)

