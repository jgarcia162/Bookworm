package jose.com.bookworm.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import jose.com.bookworm.BaseApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  ApplicationModule::class,
  FragmentModule::class,
  ActivityModule::class,
  DaoModule::class,
  ApiModule::class,
  SchedulerModule::class,
  ViewModelModule::class
]
)
interface ApplicationComponent : AndroidInjector<BaseApplication> {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: BaseApplication): Builder
    fun applicationModule(appModule: ApplicationModule): Builder
    fun build(): ApplicationComponent
  }
  
}
