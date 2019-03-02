package jose.com.bookworm

import android.content.Context
import android.preference.PreferenceManager
import androidx.core.content.edit

class SharedPreferencesHelper(context: Context){
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getFilters(): MutableSet<String>? = prefs.getStringSet(KEY_FILTERS, emptySet())

    fun saveFilters(filters: MutableSet<String>) {
        prefs.edit().putStringSet(KEY_FILTERS, filters).apply()
    }

    fun getCategories(): MutableSet<String>? = prefs.getStringSet(KEY_CATEGORIES, emptySet())

    fun saveCategories(categories: MutableSet<String>) {
        prefs.edit{
            putStringSet(KEY_CATEGORIES, categories).apply()
        }
    }

    companion object {
        const val KEY_FILTERS = "filters"
        const val KEY_CATEGORIES = "categories"
    }
}