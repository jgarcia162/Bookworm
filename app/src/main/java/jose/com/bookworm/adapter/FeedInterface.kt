package jose.com.bookworm.adapter

import jose.com.bookworm.model.nytimes.NYTimesBook

interface FeedInterface {
  fun clickBook(book: NYTimesBook)
}
