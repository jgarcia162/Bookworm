package jose.com.bookworm.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import jose.com.bookworm.R
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

    addBookButton!!.setOnClickListener { }

    val listener = View.OnClickListener { }

    addBookButton.setOnClickListener(listener)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when(item?.itemId){
      R.id.add_menu_option -> showAddBookFragment()
    }
    return true
  }

  private fun showAddBookFragment() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun showAllBooks(view: View) { startActivity(intent) }
}

