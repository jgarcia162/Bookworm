package jose.com.bookworm.model.roommodel

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import jose.com.bookworm.room.Converters
import jose.com.bookworm.room.User

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val bookId: Long,
    val title: String,
    val author: String?,
    val description: String,
    val yearPublished: Int?,
    val pages: Int?,
    @TypeConverters(Converters::class)
    val categories: List<String>,
    val isbn: Int,
    val isFinished: Boolean,
    val isCurrentlyReading: Boolean,
    val isInLibrary: Boolean,
    @Embedded(prefix = "usr_")
    val borrowedBy: User?,
    val progressPercentage: Double
)
