package jose.com.bookworm.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named

@Module
class SchedulerModule {
    @Provides
    @Named("ioScheduler")
    fun provideIOScheduler() = Schedulers.io()

    @Provides
    @Named("mainThreadScheduler")
    fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
