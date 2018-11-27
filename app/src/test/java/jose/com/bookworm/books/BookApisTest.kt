package jose.com.bookworm.books

import io.reactivex.observers.TestObserver
import jose.com.bookworm.model.VolumesResponse
import jose.com.bookworm.model.nytimes.BestSellersListNamesResponse
import jose.com.bookworm.model.nytimes.BestSellersListResponse
import jose.com.bookworm.model.nytimes.BestSellersOverviewList
import org.junit.Test
import java.util.concurrent.TimeUnit.SECONDS

/** Tests to verify [NYTimesApi] and [BooksApi] requests */

class BookApisTest: BaseApiTest(){
    @Test
    fun `When client searches for a Volume by title, Volume is successfully retrieved`(){
        mockWebServer.enqueue(successfulFindVolumeResponse)

        val testObserver = TestObserver<VolumesResponse>()
        val response = client.searchByTitle("Lolita")

        response.subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, SECONDS)
        testObserver.assertNoErrors()
    }

    @Test
    fun `When client searches for a Volume by author, Volume is successfully retrieved`(){
        mockWebServer.enqueue(successfulFindVolumeResponse)

        val testObserver = TestObserver<VolumesResponse>()
        val response = client.searchByAuthor("Ellen Pifer")

        response.subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, SECONDS)
        testObserver.assertNoErrors()
    }

    @Test
    fun `When client searches for a Volume by isbn, Volume is successfully retrieved`(){
        mockWebServer.enqueue(successfulFindVolumeResponse)

        val testObserver = TestObserver<VolumesResponse>()
        val response = client.searchByISBN("9780195150339")

        response.subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, SECONDS)
        testObserver.assertNoErrors()
    }

    @Test
    fun `Verify client search for a list by name`(){
        mockWebServer.enqueue(successfulGetBestSellersListResponse)

        val testObserver = TestObserver<BestSellersListResponse>()
        val response = client.getBestSellersList("Travel")

        response.subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, SECONDS)
        testObserver.assertNoErrors()
    }

    @Test
    fun `Verify client search for best sellers lists names`(){
        mockWebServer.enqueue(successfulGetBestSellersListNamesResponse)

        val testObserver = TestObserver<BestSellersListNamesResponse>()
        val response = client.getBestSellersListNames()

        response.subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, SECONDS)
        testObserver.assertNoErrors()
    }

    @Test
    fun `Verify client search for best sellers overview`(){
        mockWebServer.enqueue(successfulGetTopFiveBestSellersResponse)

        val testObserver = TestObserver<List<BestSellersOverviewList>>()
        val response = client.getTopFiveBestSellers()

        response.subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, SECONDS)
        testObserver.assertNoErrors()
    }
}