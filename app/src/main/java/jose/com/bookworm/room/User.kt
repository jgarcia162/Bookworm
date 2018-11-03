package jose.com.bookworm.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import jose.com.bookworm.model.Book

data class User(
    @ColumnInfo(name = "name")
    val name: String,
    @TypeConverters(Converters::class)
    val books: List<Book>,
    val phoneNumber: Int
)