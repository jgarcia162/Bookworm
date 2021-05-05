package jose.com.bookworm.views

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import jose.com.bookworm.R
import jose.com.bookworm.databinding.ActivityMainBinding
import jose.com.bookworm.extensions.toast

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AddBookDialogFragment.AddBookInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var host: NavHostFragment
    private lateinit var navController: NavController
    private val appBarConfiguration =
        AppBarConfiguration(setOf(R.id.feedFragment, R.id.libraryFragment))
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "BookWorm"
        host = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        navController.navigate(R.id.feedFragment)
        
        binding.bottomNavigationView.setupWithNavController(navController)
        
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_home -> {
                    host.navController.navigate(R.id.feedFragment)
                }
                R.id.menu_item_search -> {
                }
                R.id.menu_item_library -> {
                    host.navController.navigate(R.id.libraryFragment)
                }
                R.id.menu_item_settings -> {
                    toast("Settings clicked")
                }
            }
            true
        }
    }
    
    override fun onStart() {
        super.onStart()
        setupActionBar(navController, appBarConfiguration)
    }
    
    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfig)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.setGroupVisible(R.id.main_menu_group, true)
        return true
    }
    
    //TODO handle add button press
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.add_menu_option -> showAddBookFragment()
//            android.R.id.home -> navController.navigateUp()
//        }
//        return true
//    }
    
    private fun showAddBookFragment() {
        AddBookDialogFragment(this).show(
            supportFragmentManager,
            AddBookDialogFragment::class.simpleName
        )
    }
    
    override fun onAddBookComplete() {
        toast(getString(R.string.msg_book_added))
    }
    
    override fun onAddBookError() {
        toast(getString(R.string.error_adding_book))
    }
}
