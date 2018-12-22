package jose.com.bookworm.presenters

import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.di.Injector
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.presentations.AddBookPresentation
import jose.com.bookworm.repository.BookRepository
import javax.inject.Inject

class AddBookPresenter(val prefHelper: SharedPreferencesHelper) {
    @Inject
    lateinit var repository: BookRepository
    private var presentation: AddBookPresentation? = null

    fun attach(presentation: AddBookPresentation) {
        this.presentation = presentation

        Injector.applicationComponent.inject(this)
    }

    fun detach() {
        this.presentation = null
    }

    fun getCategories() = prefHelper.getCategories()

    fun addBook(
        title: String,
        author: String,
        isbn: String,
        pages: String,
        year: String,
        category: String
    ) {
        val defPages: Int = if (pages.isBlank()) {
            0
        } else {
            pages.toInt()
        }

        val defYear: Int = if (year.isBlank()) {
            0
        } else {
            year.toInt()
        }

        repository.addBook(
            Book(
                title = title,
                author = author,
                isbn = isbn,
                pages = defPages,
                yearPublished = defYear,
                categories = category
            )
        )

    }
}