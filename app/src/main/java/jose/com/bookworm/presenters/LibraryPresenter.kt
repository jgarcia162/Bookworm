package jose.com.bookworm.presenters

import jose.com.bookworm.model.Book
import jose.com.bookworm.presentations.LibraryPresentation

class LibraryPresenter {
    private var presentation: LibraryPresentation? = null

    fun attach(presentation: LibraryPresentation){
        this.presentation = presentation
    }

    fun detach(){
        presentation = null
    }

    fun deleteBook(book: Book){
        //TODO deletes book from db
    }

    fun deleteAllBooks(){
        //TODO deletes all books from db
    }

    fun showBookDetails(){
        //TODO shows book details fragment
    }

    fun sortBooks(sortBy: String){
        //TODO sort books
    }

    fun searchBook(searchTerms: String){
        //TODO search by text
    }
}