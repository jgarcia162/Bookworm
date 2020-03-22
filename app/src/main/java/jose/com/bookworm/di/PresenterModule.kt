package jose.com.bookworm.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presenters.*
import jose.com.bookworm.repository.BookRepository
import javax.inject.Named

@Module(includes = [ApiModule::class, ApplicationModule::class, SchedulerModule::class])
class PresenterModule{

    @Provides
    fun provideFeedPresenter(
        repository: BookRepository,
        @Named("ioScheduler") scheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
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
}
