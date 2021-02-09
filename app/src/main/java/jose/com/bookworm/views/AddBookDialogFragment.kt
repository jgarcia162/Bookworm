package jose.com.bookworm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.viewmodel.AddBookViewModel
import kotlinx.android.synthetic.main.add_book_layout.*
import kotlinx.android.synthetic.main.dialog_buttons_layout.*
import javax.inject.Inject

@AndroidEntryPoint
class AddBookDialogFragment : DialogFragment() {
    private val viewModel: AddBookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategoriesLiveData().observe(viewLifecycleOwner, Observer(::setCategories))

        done_button.onClick {
            viewModel.addBook(
                getTitle(),
                getAuthor(),
                getISBN(),
                getPages(),
                getYearPublished(),
                getGenre()
            )
        }

        clear_button.onClick {
            clearFields()
        }

        cancel_button.onClick { dismiss() }
    }

    private fun setCategories(categories: MutableSet<String>) {
        categories_spinner.adapter =
            ArrayAdapter(requireContext(), R.layout.category_list_item, categories.toMutableList())
    }

    private fun getTitle() = title_input_et.text.toString()
    private fun getAuthor() = author_input_et.text.toString()
    private fun getISBN() = isbn_input_et.text.toString()
    private fun getPages() = pages_input_et.text.toString()
    private fun getYearPublished() = year_published_input_et.text.toString()
    private fun getGenre() = categories_spinner.selectedItem.toString()

    private fun clearFields() {
        title_input_et.setText("")
        author_input_et.setText("")
        pages_input_et.setText("")
        year_published_input_et.setText("")
        categories_spinner.setSelection(0)
        isbn_input_et.setText("")
    }
}