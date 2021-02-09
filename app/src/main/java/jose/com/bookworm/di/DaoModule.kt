package jose.com.bookworm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jose.com.bookworm.room.BookDao
import jose.com.bookworm.room.BookDatabase
import jose.com.bookworm.room.DatabaseHelper

/**
 * Module which provides all dependencies for Room database
 * */

@InstallIn(SingletonComponent::class)
@Module
object DaoModule{

    @Provides
    fun provideDatabaseHelper(bookDao: BookDao): DatabaseHelper = DatabaseHelper(bookDao)

    @Provides
    fun provideBookDatabase(@ApplicationContext context: Context): BookDatabase = BookDatabase.getAppDatabase(context)!!

    @Provides
    fun provideBookDao(db: BookDatabase): BookDao = db.bookDao()
}