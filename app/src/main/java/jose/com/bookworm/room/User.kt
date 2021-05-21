package jose.com.bookworm.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import jose.com.bookworm.model.roommodel.Book
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
  @ColumnInfo(name = "name")
  val name: String,
  @TypeConverters(Converters::class)
  val books: List<Book>,
  val phoneNumber: Int
) : Parcelable