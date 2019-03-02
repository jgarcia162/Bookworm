package jose.com.bookworm.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.repository.BookRepository
import jose.com.bookworm.room.DatabaseHelper
import javax.inject.Singleton

@Module(includes = [DaoModule::class, ApiModule::class])
class ApplicationModule(val app: Application) {
    @Provides
    fun provideContext(): Context = app

    @Provides
    fun provideSharedPreferencesHelper(context: Context): SharedPreferencesHelper =
        SharedPreferencesHelper(context)

    @Provides
    @Singleton
    fun provideBookRepository(
        apiClient: ApiClient,
        databaseHelper: DatabaseHelper
    ): BookRepository {
        return BookRepository(
            apiClient = apiClient,
            databaseHelper = databaseHelper
        )
    }
}
