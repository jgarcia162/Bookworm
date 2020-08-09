package jose.com.bookworm.books

import android.content.Context
import jose.com.bookworm.network.ApiClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.mockito.Mockito.mock

open class BaseApiTest {
    lateinit var context: Context
    lateinit var mockWebServer: MockWebServer
    lateinit var client: ApiClient
    lateinit var mockBaseUrl: String
    lateinit var loggingInterceptor: HttpLoggingInterceptor

    @Before
    open fun setup() {
        context = mock(Context::class.java)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        mockBaseUrl = mockWebServer
            .url("/")
            .toString()

        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        client = ApiClient(
         loggingInterceptor
        ).apply {
            booksBaseUrl = mockBaseUrl
            nyTimesBaseUrl = mockBaseUrl
        }
    }

    @After
    open fun teardown() {
        mockWebServer.shutdown()
    }
}