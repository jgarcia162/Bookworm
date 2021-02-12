package jose.com.bookworm.views.library

import jose.com.bookworm.model.roommodel.Book

interface LibraryInterface {
  fun showBookDeleted()
  
  fun showLoading()
  
  fun hideLoading()
  
  fun showEmptyLibrary()
  
  fun hideEmptyLibrary()
  
  fun showSortedBooks()
  
  fun showBookDetails(book: Book)
}