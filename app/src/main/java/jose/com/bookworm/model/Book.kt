package jose.com.bookworm.model

/**
 * Created by Jose G. on 10/10/17.
 */

data class Book(
  val bookId: Long,
  val title: String,
  val author: String?,
  val yearPublished: Int?,
  val pages: Int?,
  val isFinished: Boolean
)
