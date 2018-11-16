package jose.com.bookworm.model.nytimes

data class TimesBook(
    val title: String,
    val author: String,
    val description: String,
    val publisher: String,
    val isbns: List<TimesISBN>
)