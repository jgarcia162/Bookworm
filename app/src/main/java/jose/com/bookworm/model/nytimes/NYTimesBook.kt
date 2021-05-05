package jose.com.bookworm.model.nytimes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NYTimesBook(
  var title: String,
  var author: String,
  var bookImageUrl: String,
) : Parcelable

