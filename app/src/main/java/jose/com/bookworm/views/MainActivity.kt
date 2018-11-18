package jose.com.bookworm.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import jose.com.bookworm.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    title = "BookWorm"

    supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container, FeedFragment()).commit()
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
    Timber.d("ADDED BOOK")
  }
}
