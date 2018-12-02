package jose.com.bookworm

import android.content.Context
import android.preference.PreferenceManager

interface PreferenceHelper {
    fun getFilters():MutableSet<String>
    fun saveFilters(filters: MutableSet<String>)
}

class SharedPreferencesHelper(context: Context): PreferenceHelper{
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getFilters(): MutableSet<String> = prefs.getStringSet(FILTERS_KEY, emptySet())

    override fun saveFilters(filters: MutableSet<String>) {
        prefs.edit().putStringSet(FILTERS_KEY, filters).apply()
    }

    companion object {
        const val FILTERS_KEY = "filters"
    }
}