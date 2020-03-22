package jose.com.bookworm.di

import dagger.Component
import jose.com.bookworm.presenters.*
import jose.com.bookworm.views.*
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DaoModule::class, ApiModule::class, PresenterModule::class])
interface ApplicationComponent {
    
    fun inject(feedPresenter: FeedPresenter)

    fun inject(feedFragment: FeedFragment)

    fun inject(bookDetailsFragment: BookDetailsFragment)

    fun inject(libraryFragment: LibraryFragment)

    fun inject(addBookDialogFragment: AddBookDialogFragment)
    
    fun inject(searchFragment: SearchFragment)
}
