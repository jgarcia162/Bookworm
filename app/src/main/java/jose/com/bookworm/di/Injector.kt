package jose.com.bookworm.di

import jose.com.bookworm.BookWormApplication.Companion.APP_CONTEXT

object Injector{
    val applicationComponent: ApplicationComponent =
            DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(APP_CONTEXT))
                .daoModule(DaoModule())
                .build()
}