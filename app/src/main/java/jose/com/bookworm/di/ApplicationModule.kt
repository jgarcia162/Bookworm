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
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module(includes = [DaoModule::class])
class ApplicationModule(val app: Application) {

    private val httpLoggingInterceptor = HttpLoggingInterceptor{ it ->
        Timber.d("LOGGER: $it")
    }
    @Provides
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient{
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return ApiClient(
            loggingInterceptor = httpLoggingInterceptor
        )
    }

    @Provides
    fun provideFeedPresenter(): FeedPresenter{
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return FeedPresenter(
            apiClient = ApiClient(httpLoggingInterceptor),
            mainThreadScheduler = AndroidSchedulers.mainThread(),
            ioScheduler = Schedulers.io()
        )
    }

    @Provides
    fun provideBookDetailsPresenter(): BookDetailsPresenter{
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return BookDetailsPresenter(apiClient = ApiClient(httpLoggingInterceptor))
    }

    @Provides
    fun provideLibraryPresenter(): LibraryPresenter{
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return LibraryPresenter(apiClient = ApiClient(httpLoggingInterceptor))
    }

    @Provides
    fun provideSearchPresenter(): SearchPresenter{
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return SearchPresenter(apiClient = ApiClient(httpLoggingInterceptor))
    }
}
