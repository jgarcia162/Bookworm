package jose.com.bookworm.di

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presenters.*
import jose.com.bookworm.repository.BookRepository

@Module(includes = [ApiModule::class, ApplicationModule::class])
class PresenterModule{
    private val mainThreadScheduler = AndroidSchedulers.mainThread()
    private val scheduler = Schedulers.io()

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
    fun provideBookDetailsPresenter(apiClient: ApiClient): BookDetailsPresenter =
        BookDetailsPresenter(apiClient)

    @Provides
    fun provideLibraryPresenter(apiClient: ApiClient): LibraryPresenter =
        LibraryPresenter(apiClient)


    @Provides
    fun provideSearchPresenter(apiClient: ApiClient): SearchPresenter = SearchPresenter(apiClient)

    @Provides
    fun provideAddBookPresenter(prefHelper: SharedPreferencesHelper): AddBookPresenter = AddBookPresenter(prefHelper)
}