package jose.com.bookworm.model

/**
 * Created by Joe on 10/10/17.
 */

class Book {
  var id: Long = 0

  lateinit var title: String

  lateinit var author: String

  lateinit var nameOfBorrower: String

  var isFinishedReading: Boolean = false
  var isReturned: Boolean = false

}
