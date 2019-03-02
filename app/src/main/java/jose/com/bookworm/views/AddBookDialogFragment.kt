package jose.com.bookworm.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import jose.com.bookworm.R
import jose.com.bookworm.di.Injector
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.presentations.AddBookPresentation
import jose.com.bookworm.presenters.AddBookPresenter
import kotlinx.android.synthetic.main.add_book_layout.*
import kotlinx.android.synthetic.main.dialog_buttons_layout.*
import javax.inject.Inject

class AddBookDialogFragment : DialogFragment(), AddBookPresentation {
    @Inject
    lateinit var presenter: AddBookPresenter

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

        scan_image_button.onClick {
            startActivityForResult(Intent(context, ScanBarcodeActivity::class.java), 1)
        }

        done_button.onClick {
            presenter.addBook(
                getTitle(),
                getAuthor(),
                getISBN(),
                getPages(),
                getYearPublished()
            )
        }

        clear_button.onClick { clearFields() }
        cancel_button.onClick { dismiss() }
    }

    override fun setCategories(categories: MutableSet<String>?) {}

    fun getTitle() = title_input_et.text.toString()
    fun getAuthor() = author_input_et.text.toString()
    fun getISBN() = isbn_input_et.text.toString()
    fun getPages() = pages_input_et.text.toString()
    fun getYearPublished() = year_published_input_et.text.toString()

    private fun clearFields() {
        title_input_et.setText("")
        author_input_et.setText("")
        pages_input_et.setText("")
        year_published_input_et.setText("")
        isbn_input_et.setText("")
    }
}