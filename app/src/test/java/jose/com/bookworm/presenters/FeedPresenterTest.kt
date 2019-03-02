package jose.com.bookworm.presenters

import androidx.room.Room
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.schedulers.Schedulers
import jose.com.bookworm.books.BaseApiTest
import jose.com.bookworm.books.successfulGetBestSellersListNamesResponse
import jose.com.bookworm.books.successfulGetBestSellersListResponse
import jose.com.bookworm.books.successfulGetTopFiveBestSellersResponse
import jose.com.bookworm.model.nytimes.BestSellersBook
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.presentations.FeedPresentation
import jose.com.bookworm.repository.BookRepository
import jose.com.bookworm.room.BookDatabase
import jose.com.bookworm.room.DatabaseHelper
import org.awaitility.Awaitility.await
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit.SECONDS

class FeedPresenterTest : BaseApiTest() {
    private lateinit var presenter: FeedPresenter
    private lateinit var presentation: FeedPresentation
    private lateinit var database: BookDatabase

    @Before
    override fun setup() {
        super.setup()
        presentation = mock()
        database = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java).build()

        presenter = FeedPresenter(
            BookRepository(client, DatabaseHelper(database.bookDao())),
            Schedulers.io(),
            Schedulers.io()
        )
    }

    @After
    override fun teardown(){
        super.teardown()
        database.close()
    }

    @Test
    fun `When presenter is attached, it successfully shows best sellers`() {
        mockWebServer.enqueue(successfulGetTopFiveBestSellersResponse)

        val bestSellerBook = BestSellersOverviewBook(
            amazonUrl = "https://www.amazon.com/Past-Tense-Jack-Reacher-Novel/dp/0399593519?tag=NYTBS-20",
            author = "Lee Child",
            bookImage = "https://s1.nyt.com/du/books/images/9780399593512.jpg",
            description = "Jack Reacher explores the New England town where his father was born and a Canadian couple now find themselves stranded.",
            isbn13 = "9781473542303",
            publisher = "Delacorte",
            rank = 1,
            title = "PAST TENSE",
            buyLinks = emptyList()
        )

        presenter.attach(presentation)

        var bestSellersLoaded = false

        presenter.getBestSellersOverview { bestSellersLoaded = true }

        verify(presentation).showLoading()

        await()
            .atMost(2, SECONDS)
            .until { bestSellersLoaded }

        verify(presentation).hideLoading()
        verify(presentation).showBestSellersList(listOf(bestSellerBook))
        verify(presentation).showGetBestSellersSuccess()
        verifyNoMoreInteractions(presentation)
    }

    @Test
    fun `When FeedFragment is attached, best-sellers chip group is populated`() {
        mockWebServer.enqueue(successfulGetBestSellersListNamesResponse)

        presenter.attach(presentation)

        var listLoaded = false

        presenter.getBestSellersListNames { listLoaded = true }

        await()
            .atMost(2, SECONDS)
            .until { listLoaded }

        verify(presentation).loadListNamesChips(mutableSetOf("Combined Print & E-Book Fiction"))
        verifyNoMoreInteractions(presentation)
    }

    @Test
    fun `When best-seller Chip is selected, best-seller list is loaded`() {
        mockWebServer.enqueue(successfulGetBestSellersListResponse)

        val book = BestSellersBook(
            title = "ATLAS OBSCURA",
            description = "A richly documented ultimate explorer's guide to more than 700 hidden marvels, events and curiosities around the world.",
            author = "Joshua Foer, Dylan Thuras and Ella Morton",
            publisher = "Workman",
            isbn13 = "9780761169086",
            isbn10 = "0761169083"
        )

        presenter.attach(presentation)

        var listLoaded = true

        presenter.getBestSellersList("Travel") { listLoaded = true }

        verify(presentation).showLoading()

        await()
            .atMost(2, SECONDS)
            .until { listLoaded }

        verify(presentation).hideLoading()
        verify(presentation).showBestSellersList(mutableListOf(book))
        verify(presentation).showGetBestSellersSuccess("Travel")
    }
}