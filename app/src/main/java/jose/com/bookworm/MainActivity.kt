package jose.com.bookworm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import jose.com.bookworm.model.Book
import jose.com.bookworm.adapter.BookAdapter
import jose.com.bookworm.model.BookViewModel

class MainActivity : AppCompatActivity() {
  private val textView: TextView? = null
  private var titleEditText: EditText? = null
  private var authorEditText: EditText? = null
  private val addBookButton: Button? = null
  private val getBookButton: Button? = null
  private val allBooksList: List<Book>? = null
  private lateinit var adapter: BookAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    title = "BookWorm"

    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.hasFixedSize()

    val model = ViewModelProviders.of(this).get(BookViewModel::class.java)
    model.getBooks().observe(this, Observer<List<Book>> { books ->
        adapter = BookAdapter(books)
    })

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

  fun showAllBooks(view: View) { startActivity(intent) }
}

