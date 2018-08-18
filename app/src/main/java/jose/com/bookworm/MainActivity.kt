package jose.com.bookworm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import jose.com.bookworm.model.Book

class MainActivity : AppCompatActivity() {

  private val textView: TextView? = null
  private var titleEditText: EditText? = null
  private var authorEditText: EditText? = null
  private val addBookButton: Button? = null
  private val getBookButton: Button? = null

  private val allBooksList: List<Book>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    title = "BookWorm"

    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.hasFixedSize()
    val adapter = BookAdapter(allBooksList)
    val layoutManager = LinearLayoutManager(this)

    layoutManager.orientation = LinearLayoutManager.VERTICAL
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = adapter

    titleEditText = findViewById<View>(R.id.title_edit_text) as EditText


    authorEditText = findViewById<View>(R.id.author_edit_text) as EditText


    addBookButton!!.setOnClickListener { }

    val listener = View.OnClickListener { }

    addBookButton.setOnClickListener(listener)

  }

  internal fun addBook(book: Book) {

  }

  fun showAllBooks(view: View) {
    startActivity(intent)

  }

  override fun onSaveInstanceState(outState: Bundle) {
    outState.putInt("key", 3)
    outState.putBoolean("true", true)
    super.onSaveInstanceState(outState)

  }
}

