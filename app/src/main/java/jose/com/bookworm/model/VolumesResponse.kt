package jose.com.bookworm.model

import jose.com.bookworm.model.googlebooks.Volume

data class VolumesResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Volume>
)

