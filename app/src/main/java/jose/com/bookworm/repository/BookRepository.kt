package jose.com.bookworm.repository

import jose.com.bookworm.network.ApiClient
import jose.com.bookworm.room.BookDao

class BookRepository(
    private val apiClient: ApiClient,
    private val bookDao: BookDao
) {

    fun getBestSellersOverview() = apiClient.getBestSellersOverview()

    fun getBestSellersListNames() = apiClient.getBestSellersListNames()

    fun getBestSellersList(listName: String) = apiClient.getBestSellersList(listName)

    fun getCurrentReadings() = bookDao.getAllBooks().filter { it.isCurrentlyReading }
}
