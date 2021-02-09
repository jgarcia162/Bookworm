package jose.com.bookworm.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "BookWorm"
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, FeedFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_menu_option -> showAddBookFragment()
        }
        return true
    }

    private fun showAddBookFragment() {
        AddBookDialogFragment().show(supportFragmentManager, "add_book_fragment")
    }
}
