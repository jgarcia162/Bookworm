package jose.com.bookworm.room

import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import jose.com.bookworm.model.roommodel.Book

data class User(
    @ColumnInfo(name = "name")
    val name: String,
    @TypeConverters(Converters::class)
    val books: List<Book>,
    val phoneNumber: Int
)