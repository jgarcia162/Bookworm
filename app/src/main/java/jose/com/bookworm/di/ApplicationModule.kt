package jose.com.bookworm.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presenters.BookDetailsPresenter
import jose.com.bookworm.presenters.FeedPresenter
import jose.com.bookworm.presenters.LibraryPresenter
import jose.com.bookworm.presenters.SearchPresenter
import jose.com.bookworm.repository.BookRepository
import jose.com.bookworm.room.DatabaseHelper
import javax.inject.Singleton

@Module(includes = [DaoModule::class, ApiModule::class])
class ApplicationModule(val app: Application) {
    private val mainThreadScheduler = AndroidSchedulers.mainThread()
    private val scheduler = Schedulers.io()

    @Provides
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideBookRepository(
        apiClient: ApiClient,
        databaseHelper: DatabaseHelper
    ): BookRepository{
        return BookRepository(
            apiClient = apiClient,
            databaseHelper = databaseHelper
        )
    }

    @Provides
    fun provideFeedPresenter(
        repository: BookRepository
    ): FeedPresenter {
        return FeedPresenter(
            repository = repository,
            mainThreadScheduler = mainThreadScheduler,
            ioScheduler = scheduler
        )
    }

    @Provides
    fun provideBookDetailsPresenter(apiClient: ApiClient): BookDetailsPresenter {
        return BookDetailsPresenter(apiClient)
    }

    @Provides
    fun provideLibraryPresenter(apiClient: ApiClient): LibraryPresenter {
        return LibraryPresenter(apiClient)
    }

    @Provides
    fun provideSearchPresenter(apiClient: ApiClient): SearchPresenter {
        return SearchPresenter(apiClient)
    }
}
