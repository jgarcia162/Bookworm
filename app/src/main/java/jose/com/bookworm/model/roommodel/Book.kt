package jose.com.bookworm.model.roommodel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import jose.com.bookworm.room.User

@Entity(tableName = "books")
data class Book(
  @PrimaryKey(autoGenerate = true) val bookId: Long = 0,
  val title: String,
  val author: String?,
  val description: String? = "",
  val yearPublished: Int?,
  val pages: Int?,
  val categories: String? = "",
  val isbn: String,
  val coverUrl: String? = null,
  val isFinished: Boolean = false,
  val isCurrentlyReading: Boolean = false,
  val isInLibrary: Boolean = true,
  @Embedded(prefix = "usr_")
  val borrowedBy: User? = null,
  val progressPercentage: Double = 0.0
)
