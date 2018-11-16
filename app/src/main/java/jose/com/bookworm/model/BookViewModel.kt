package jose.com.bookworm.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import jose.com.bookworm.model.roommodel.Book

class BookViewModel : ViewModel(){
    private lateinit var books: MutableLiveData<List<Book>>

    fun getBooks(): LiveData<List<Book>>{
        if(!::books.isInitialized){
            books = MutableLiveData()
            loadBooks()
        }
        return books
    }

    private fun loadBooks(){
        //TODO load books from database
    }
}