package jose.com.bookworm.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.repository.BookRepository
import jose.com.bookworm.room.DatabaseHelper
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule{

    @Provides
    fun provideSharedPreferencesHelper(@ApplicationContext context: Context): SharedPreferencesHelper =
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
