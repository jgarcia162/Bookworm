package jose.com.bookworm

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(context: Context) {
  private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
  
  fun getFilters(): MutableSet<String>? = prefs.getStringSet(FILTERS_KEY, emptySet())
  
  fun saveFilters(filters: MutableSet<String>) {
    prefs.edit().putStringSet(FILTERS_KEY, filters).apply()
  }
  
  fun getCategories(): MutableSet<String>? = prefs.getStringSet(CATEGORIES_KEY, emptySet())
  
  fun saveCategories(filters: MutableSet<String>) =
    prefs.edit().putStringSet(CATEGORIES_KEY, filters).apply()
  
  companion object {
    const val FILTERS_KEY = "filters"
    const val CATEGORIES_KEY = "categories"
  }
}