package jose.com.bookworm.presenters

import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.di.Injector
import jose.com.bookworm.presentations.AddBookPresentation
import jose.com.bookworm.repository.BookRepository
import javax.inject.Inject

class AddBookPresenter{
    @Inject lateinit var prefHelper: SharedPreferencesHelper
    @Inject lateinit var repository: BookRepository
    private var presentation: AddBookPresentation? = null

    fun attach(presentation: AddBookPresentation){
        this.presentation = presentation

        Injector.applicationComponent.inject(this)
    }

    fun detach(){
        this.presentation = null
    }

    fun getCategories() = prefHelper.getCategories()?.map { listOf(it) }
}