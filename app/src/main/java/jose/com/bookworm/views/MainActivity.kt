package jose.com.bookworm.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.extensions.toast
import jose.com.bookworm.views.library.LibraryFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AddBookDialogFragment.AddBookInterface {
    @Inject
    lateinit var feedFragment: FeedFragment
    @Inject
    lateinit var searchFragment: SearchFragment
    @Inject
    lateinit var libraryFragment: LibraryFragment
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "BookWorm"
        supportFragmentManager.beginTransaction()
          .replace(R.id.main_fragment_container, FeedFragment()).commit()
        
        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_home -> {
                    supportFragmentManager
                      .beginTransaction()
                      .replace(R.id.main_fragment_container, feedFragment, FeedFragment::class.simpleName)
                      .commit()
                }
                R.id.menu_item_search -> {
                    supportFragmentManager
                      .beginTransaction()
                      .replace(R.id.main_fragment_container, searchFragment, SearchFragment::class.simpleName)
                      .commit()
                }
                R.id.menu_item_library -> {
                    supportFragmentManager
                      .beginTransaction()
                      .replace(R.id.main_fragment_container, libraryFragment, LibraryFragment::class.simpleName)
                      .commit()
                }
                R.id.menu_item_settings -> {
                    toast("Settings clicked")
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.setGroupVisible(R.id.main_menu_group, true)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_menu_option -> showAddBookFragment(supportFragmentManager)
        }
        return true
    }
    
    private fun showAddBookFragment(fragmentManager: FragmentManager) {
        AddBookDialogFragment(this).show(fragmentManager, AddBookDialogFragment::class.simpleName)
    }
    
    override fun onAddBookComplete() {
        toast(getString(R.string.msg_book_added))
    }
    
    override fun onAddBookError() {
        toast(getString(R.string.error_adding_book))
    }
}
