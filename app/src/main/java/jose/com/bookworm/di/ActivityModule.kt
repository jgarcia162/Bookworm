package jose.com.bookworm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jose.com.bookworm.views.MainActivity

@Module
abstract class ActivityModule {
  
  @ContributesAndroidInjector
  abstract fun getMainActivity() : MainActivity
}