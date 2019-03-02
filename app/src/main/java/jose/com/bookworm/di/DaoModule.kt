package jose.com.bookworm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import jose.com.bookworm.room.BookDao
import jose.com.bookworm.room.BookDatabase
import jose.com.bookworm.room.DatabaseHelper

/**
 * Module which provides all dependencies for Room database
 * */
@Module(includes = [ApplicationModule::class])
class DaoModule{

    @Provides
    fun provideDatabaseHelper(bookDao: BookDao): DatabaseHelper = DatabaseHelper(bookDao)

    @Provides
    fun provideBookDatabase(context: Context): BookDatabase = BookDatabase.getAppDatabase(context)!!

    @Provides
    fun provideBookDao(db: BookDatabase): BookDao = db.bookDao()
}