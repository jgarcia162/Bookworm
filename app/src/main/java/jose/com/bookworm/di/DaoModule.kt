package jose.com.bookworm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import jose.com.bookworm.room.BookDao
import jose.com.bookworm.room.BookDatabase

/**
 * Module which provides all dependencies for Room database
 * */
@Module(includes = [ApplicationModule::class])
class DaoModule{
    /**
     * Returns the database of the application.
     * @param context Context in which the application is running
     * @return the database of the application
     */
    @Provides
    fun provideBookDatabase(context: Context): BookDatabase = BookDatabase.getAppDatabase(context)!!

    /**
     * Returns the Dao of Books.
     * @param db the database of the application
     * @return the Dao of Books
     */
    @Provides
    fun provideBookDao(db: BookDatabase): BookDao = db.bookDao()
}