package jose.com.bookworm.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jose.com.bookworm.model.roommodel.Book

class Converters {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToListOfBooks(data: String?): List<Book> {
        if (data == null) {
            return mutableListOf()
        }

        val listType = object : TypeToken<List<Book>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun bookListToString(list: List<Book>): String = gson.toJson(list)

    @TypeConverter
    fun stringToListOfStrings(data: String?): List<String> {
        if (data == null) {
            return mutableListOf()
        }

        val listType = object : TypeToken<List<String>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun categoriesListToString(list: List<String>): String = gson.toJson(list)
}
