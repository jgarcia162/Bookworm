package jose.com.bookworm.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jose.com.bookworm.viewmodel.AddBookViewModel
import jose.com.bookworm.viewmodel.ViewModelKey

@Module
abstract class ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(AddBookViewModel::class)
    abstract fun bindMainViewModel(addBookViewModel: AddBookViewModel): ViewModel?
}