package jose.com.bookworm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.repository.BookRepository
import javax.inject.Inject

class AddBookViewModel @Inject constructor(
    private val repository: BookRepository,
    private val prefHelper: SharedPreferencesHelper
) : ViewModel() {
    val categoriesLiveData = MutableLiveData<MutableSet<String>>()

    fun getCategories() {
        categoriesLiveData.postValue(prefHelper.getCategories())
    }

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

    override fun onCleared() {
        super.onCleared()
    }
}