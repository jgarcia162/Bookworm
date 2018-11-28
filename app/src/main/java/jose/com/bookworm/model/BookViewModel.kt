package jose.com.bookworm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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