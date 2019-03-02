package jose.com.bookworm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jose.com.bookworm.model.roommodel.Book

@Database(entities = [Book::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    fun destroyDatabaseInstance() {
        database = null
    }

    companion object {
        @JvmStatic
        var database: BookDatabase? = null
        private const val DATABASE_NAME: String = "bookworm_database"

        fun getAppDatabase(context: Context): BookDatabase? {
            if (database == null) {
                synchronized(BookDatabase::class) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return database
        }
    }
}