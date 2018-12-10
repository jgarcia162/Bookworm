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
import jose.com.bookworm.room.BookDatabase
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module(includes = [DaoModule::class])
class ApplicationModule(val app: Application) {

    private val httpLoggingInterceptor = HttpLoggingInterceptor { it ->
        Timber.d("LOGGER: $it")
    }.apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return ApiClient(
            loggingInterceptor = httpLoggingInterceptor
        )
    }

    @Provides
    @Singleton
    fun provideBookRespository(): BookRepository{
        return BookRepository(
            apiClient = provideApiClient(),
            bookDao = BookDatabase.getAppDatabase(app)!!.bookDao()
        )
    }

    @Provides
    fun provideFeedPresenter(): FeedPresenter {
        return FeedPresenter(
            context = app,
            repository = provideBookRespository(),
            mainThreadScheduler = AndroidSchedulers.mainThread(),
            ioScheduler = Schedulers.io()
        )
    }

    @Provides
    fun provideBookDetailsPresenter(): BookDetailsPresenter {
        return BookDetailsPresenter(provideApiClient())
    }

    @Provides
    fun provideLibraryPresenter(): LibraryPresenter {
        return LibraryPresenter(provideApiClient())
    }

    @Provides
    fun provideSearchPresenter(): SearchPresenter {
        return SearchPresenter(provideApiClient())
    }
}
