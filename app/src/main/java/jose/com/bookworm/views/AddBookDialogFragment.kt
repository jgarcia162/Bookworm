package jose.com.bookworm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import jose.com.bookworm.R
import jose.com.bookworm.di.Injector
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.repository.BookRepository
import kotlinx.android.synthetic.main.add_book_layout.*
import kotlinx.android.synthetic.main.dialog_buttons_layout.*
import javax.inject.Inject

class AddBookDialogFragment:DialogFragment(){
    @Inject
    lateinit var repository: BookRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        done_button.onClick {
            val book = Book(
                title = getTitle(),
                author = getAuthor(),
                isbn = getISBN().toInt(),
                pages = getPages().toInt(),
                yearPublished = getYearPublished().toInt(),
                categories = getGenre().split(",")
            )

            repository.addBook(book)
        }

        clear_button.onClick {
            clearFields()
        }

        cancel_button.onClick { dismiss() }
    }

    private fun getTitle() = title_input_et.editText?.text.toString()

    private fun getAuthor() = author_input_et.editText?.text.toString()

    private fun getISBN() = isbn_input_et.editText?.text.toString()

    private fun getPages() = pages_input_et.editText?.text.toString()

    private fun getYearPublished() = year_published_input_et.editText?.text.toString()

    private fun getGenre() = year_published_input_et.editText?.text.toString()

    private fun clearFields(){
        title_input_et.editText?.setText("")
        author_input_et.editText?.setText("")
        pages_input_et.editText?.setText("")
        year_published_input_et.editText?.setText("")
        genre_input_et.editText?.setText("")
        isbn_input_et.editText?.setText("")
    }
}