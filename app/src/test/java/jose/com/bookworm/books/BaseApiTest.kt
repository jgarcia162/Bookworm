package jose.com.bookworm.books

import jose.com.bookworm.network.ApiClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

open class BaseApiTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var client: ApiClient
    lateinit var mockBaseUrl: String

    @Before
    open fun setup(){
        mockWebServer = MockWebServer()
        mockWebServer.start()

        mockBaseUrl = mockWebServer
            .url("/")
            .toString()

        client = ApiClient(
            mockBaseUrl,
            HttpLoggingInterceptor{
                println(it)
            }.apply { level = HttpLoggingInterceptor.Level.BODY }
        )
    }

    @After
    fun teardown(){
        mockWebServer.shutdown()
    }
}
