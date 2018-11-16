package jose.com.bookworm.model.googlebooks

data class Volume(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)