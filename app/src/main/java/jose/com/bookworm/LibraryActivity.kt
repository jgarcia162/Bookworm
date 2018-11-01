package jose.com.bookworm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import jose.com.bookworm.model.Book

/**
 * Created by Joe on 10/29/17.
 */

class LibraryActivity : AppCompatActivity() {
  private var allBooksList: List<Book>? = null
  private var recyclerView: RecyclerView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_library)

    recyclerView = findViewById(R.id.recycler_view)
    recyclerView!!.hasFixedSize()
    val adapter = BookAdapter(allBooksList)
    val layoutManager = LinearLayoutManager(this)
    layoutManager.orientation = LinearLayoutManager.VERTICAL
    recyclerView!!.layoutManager = layoutManager
    recyclerView!!.adapter = adapter
  }
}
