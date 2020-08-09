package jose.com.bookworm.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jose.com.bookworm.viewmodel.*
import javax.inject.Singleton

@Module
abstract class ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(AddBookViewModel::class)
    abstract fun bindAddBookViewModel(addBookViewModel: AddBookViewModel): ViewModel
    
    @Binds
    @IntoMap
    @ViewModelKey(BookDetailsViewModel::class)
    abstract fun bindBookDetailsViewModel(bookDetailsViewModel: BookDetailsViewModel): ViewModel
    
    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun bindFeedViewModel(feedViewModel: FeedViewModel): ViewModel
    
    @Binds
    @IntoMap
    @ViewModelKey(LibraryViewModel::class)
    abstract fun bindLibraryViewModel(libraryViewModel: LibraryViewModel): ViewModel
    
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
    
    @Binds
    @Singleton
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}
