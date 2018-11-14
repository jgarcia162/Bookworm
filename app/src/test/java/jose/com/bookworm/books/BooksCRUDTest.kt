package jose.com.bookworm.books

import io.reactivex.observers.TestObserver
import jose.com.bookworm.model.Volume
import jose.com.bookworm.model.VolumesResponse
import org.junit.Test
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Tests to verify search queries for [Volume]s
 */

class BooksCRUDTest: BaseApiTest(){
    @Test
    fun `When client searches for a Volume by title, Volume is successfully retrieved`(){
        mockWebServer.enqueue(successfulFindVolumeResponse)

        val testObserver = TestObserver<VolumesResponse>()
        client.searchByTitle("Lolita").subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, SECONDS)
        testObserver.assertNoErrors()
    }
}