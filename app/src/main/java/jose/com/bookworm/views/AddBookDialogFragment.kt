package jose.com.bookworm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import jose.com.bookworm.R
import jose.com.bookworm.SharedPreferencesHelper
import jose.com.bookworm.di.Injector
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.presentations.AddBookPresentation
import jose.com.bookworm.presenters.AddBookPresenter
import jose.com.bookworm.repository.BookRepository
import kotlinx.android.synthetic.main.add_book_layout.*
import kotlinx.android.synthetic.main.dialog_buttons_layout.*
import javax.inject.Inject

class AddBookDialogFragment : DialogFragment(), AddBookPresentation {
    @Inject
    lateinit var repository: BookRepository
    @Inject
    lateinit var prefHelper: SharedPreferencesHelper
    @Inject
    lateinit var presenter: AddBookPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

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

        var list = presenter.getCategories()?.map { arrayOf(it) }

        var languages = arrayOf("Java", "PHP", "Kotlin", "Javascript", "Python", "Swift")

        //TODO create spinner adapter
//        categories_spinner.adapter =
//            ArrayAdapter(this, android.R.layout.simple_list_item_1, languages)


        done_button.onClick {
            val book = Book(
                title = getTitle(),
                author = getAuthor(),
                isbn = getISBN(),
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

    override fun setCategories(categories: MutableSet<String>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getTitle() = title_input_et.editText?.text.toString()

    private fun getAuthor() = author_input_et.editText?.text.toString()

    private fun getISBN() = isbn_input_et.editText?.text.toString()

    private fun getPages() = pages_input_et.editText?.text.toString()

    private fun getYearPublished() = year_published_input_et.editText?.text.toString()

    private fun getGenre() = year_published_input_et.editText?.text.toString()

    private fun clearFields() {
        title_input_et.editText?.setText("")
        author_input_et.editText?.setText("")
        pages_input_et.editText?.setText("")
        year_published_input_et.editText?.setText("")
//        categories_spinner.editText?.setText("")
        isbn_input_et.editText?.setText("")
    }
}