package jose.com.bookworm.adapter

import jose.com.bookworm.model.nytimes.BestSellersOverviewBook

interface FeedInterface {
  fun clickBook(book: BestSellersOverviewBook)
}
