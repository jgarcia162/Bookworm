package jose.com.bookworm

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import jose.com.bookworm.di.ApplicationComponent
import jose.com.bookworm.di.ApplicationModule
import jose.com.bookworm.di.DaggerApplicationComponent
import timber.log.Timber


class BaseApplication : DaggerApplication() {
  override fun onCreate() {
    super.onCreate()
    
    APP_CONTEXT = this
    Timber.plant(Timber.DebugTree())
  }
  
  override fun applicationInjector() =
    DaggerApplicationComponent.builder()
      .application(this)
      .applicationModule(ApplicationModule(this))
      .build()
  
  companion object {
    lateinit var APP_CONTEXT: BaseApplication
      private set
  }
}
