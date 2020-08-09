package jose.com.bookworm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jose.com.bookworm.views.*

@Module
abstract class FragmentModule {
  
  @ContributesAndroidInjector
  abstract fun getAddBookDialogFragment(): AddBookDialogFragment
  
  @ContributesAndroidInjector
  abstract fun getBookDetailsFragment(): BookDetailsFragment
  
  @ContributesAndroidInjector
  abstract fun getChipsDialogFragment(): ChipsDialogFragment
  
  @ContributesAndroidInjector
  abstract fun getFeedFragment(): FeedFragment
  
  @ContributesAndroidInjector
  abstract fun getLibraryFragment(): LibraryFragment
  
  @ContributesAndroidInjector
  abstract fun getSearchFragment(): SearchFragment
}