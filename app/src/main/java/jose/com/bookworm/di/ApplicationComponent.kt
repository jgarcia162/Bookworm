package jose.com.bookworm.di

import dagger.Component
import jose.com.bookworm.presenters.BookDetailsPresenter
import jose.com.bookworm.presenters.LibraryPresenter
import jose.com.bookworm.presenters.SearchPresenter
import jose.com.bookworm.views.BookDetailsFragment
import jose.com.bookworm.views.FeedFragment
import jose.com.bookworm.views.LibraryFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DaoModule::class, ApiModule::class])
interface ApplicationComponent {
    fun inject(bookDetailsPresenter: BookDetailsPresenter)

    fun inject(libraryPresenter: LibraryPresenter)

    fun inject(searchPresenter: SearchPresenter)

    fun inject(feedFragment: FeedFragment)

    fun inject(bookDetailsFragment: BookDetailsFragment)

    fun inject(libraryFragment: LibraryFragment)
}
