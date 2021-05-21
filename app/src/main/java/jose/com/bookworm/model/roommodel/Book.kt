package jose.com.bookworm.model.roommodel

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import jose.com.bookworm.room.User
import kotlinx.parcelize.Parcelize

@Entity(tableName = "books")
@Parcelize
data class Book(
  @PrimaryKey(autoGenerate = true) val bookId: Long = 0,
  val title: String,
  val author: String = "",
  val description: String = "",
  val yearPublished: Int = 0,
  val pages: Int = 0,
  val categories: String = "",
  val isbn: String,
  val coverUrl: String? = null,
  val isFinished: Boolean = false,
  val isCurrentlyReading: Boolean = false,
  val isInLibrary: Boolean = true,
  @Embedded(prefix = "usr_")
  val borrowedBy: User? = null,
  val progressPercentage: Double = 0.0,
) : Parcelable
