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

    @Before
    open fun setup() {
        context = mock(Context::class.java)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        mockBaseUrl = mockWebServer
            .url("/")
            .toString()

        client = ApiClient(
            HttpLoggingInterceptor {
                println(it)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).apply {
            booksBaseUrl = mockBaseUrl
            nyTimesBaseUrl = mockBaseUrl
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}