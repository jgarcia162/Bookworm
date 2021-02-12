package jose.com.bookworm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object SchedulerModule {
    @Provides
    @Named("io")
    fun provideIOScheduler(): Scheduler = Schedulers.io()
    
    @Provides
    @Named("main")
    fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
