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
import jose.com.bookworm.databinding.AddBookLayoutBinding
import jose.com.bookworm.databinding.DialogButtonsLayoutBinding
import jose.com.bookworm.databinding.FragmentAddBookBinding
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.viewmodel.AddBookViewModel

@AndroidEntryPoint
class AddBookDialogFragment(private var listener: AddBookInterface?) : DialogFragment() {
  private var fragmentAddBookBinding: FragmentAddBookBinding? = null
  private var dialogButtonsBinding: DialogButtonsLayoutBinding? = null
  private var addBookLayoutBinding: AddBookLayoutBinding? = null
  private val addBookBinding get() = fragmentAddBookBinding!!
  private val dialogBinding get() = dialogButtonsBinding!!
  private val layoutBinding get() = addBookLayoutBinding!!
  private val viewModel: AddBookViewModel by viewModels()
  
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    fragmentAddBookBinding = FragmentAddBookBinding.inflate(inflater, container, false)
    dialogButtonsBinding = DialogButtonsLayoutBinding.inflate(inflater, container, false)
    addBookLayoutBinding = AddBookLayoutBinding.inflate(inflater, container, false)
    return addBookBinding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getCategoriesLiveData().observe(viewLifecycleOwner, Observer(::setCategories))
  
    dialogBinding.doneButton.onClick {
      viewModel.addBook(
        title = getTitle(),
        author = getAuthor(),
        isbn = getISBN(),
        pages = getPages(),
        year = getYearPublished(),
        category = getGenre(),
        onAddBookComplete = {
          listener?.onAddBookComplete()
          dismiss()
        },
        onAddBookError = {
          listener?.onAddBookError()
          dismiss()
        }
      )
    }
  
    dialogBinding.clearButton.onClick {
      clearFields()
    }
  
    dialogBinding.cancelButton.onClick { dismiss() }
  }
  
  private fun setCategories(categories: MutableSet<String>) {
    layoutBinding.categoriesSpinner.adapter =
      ArrayAdapter(requireContext(), R.layout.category_list_item, categories.toMutableList())
  }
  
  private fun getTitle() = layoutBinding.titleInputEt.text.toString()
  private fun getAuthor() = layoutBinding.authorInputEt.text.toString()
  private fun getISBN() = layoutBinding.isbnInputEt.text.toString()
  private fun getPages() = layoutBinding.pagesInputEt.text.toString()
  private fun getYearPublished() = layoutBinding.yearPublishedInputEt.text.toString()
  private fun getGenre() = layoutBinding.categoriesSpinner.selectedItem.toString()
  
  private fun clearFields() {
    layoutBinding.titleInputEt.setText("")
    layoutBinding.authorInputEt.setText("")
    layoutBinding.isbnInputEt.setText("")
    layoutBinding.pagesInputEt.setText("")
    layoutBinding.yearPublishedInputEt.setText("")
    layoutBinding.categoriesSpinner.setSelection(0)
  }
  
  override fun onDestroy() {
    super.onDestroy()
    listener = null
  }
  
  interface AddBookInterface {
    fun onAddBookComplete()
    fun onAddBookError()
  }
}