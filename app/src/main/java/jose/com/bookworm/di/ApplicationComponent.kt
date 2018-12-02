package jose.com.bookworm.di

import dagger.Component
import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.presenters.BookDetailsPresenter
import jose.com.bookworm.presenters.FeedPresenter
import jose.com.bookworm.presenters.LibraryPresenter
import jose.com.bookworm.presenters.SearchPresenter
import jose.com.bookworm.room.BookDao
import jose.com.bookworm.views.BookDetailsFragment
import jose.com.bookworm.views.FeedFragment
import jose.com.bookworm.views.LibraryFragment
import jose.com.bookworm.views.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DaoModule::class])
interface ApplicationComponent {
    fun provideApiClient(): ApiClient

    fun provideFeedPresenter(): FeedPresenter

    fun provideBookDetailsPresenter(): BookDetailsPresenter

    fun provideLibraryPresenter(): LibraryPresenter

    fun provideSearchPresenter(): SearchPresenter

    fun provideBookDao(): BookDao

    fun inject(feedPresenter: FeedPresenter)

    fun inject(bookDetailsPresenter: BookDetailsPresenter)

    fun inject(libraryPresenter: LibraryPresenter)

    fun inject(searchPresenter: SearchPresenter)

    fun inject(activity: MainActivity)

    fun inject(feedFragment: FeedFragment)

    fun inject(bookDetailsFragment: BookDetailsFragment)

    fun inject(libraryFragment: LibraryFragment)
}
