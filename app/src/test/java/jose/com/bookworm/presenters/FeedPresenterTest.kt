package jose.com.bookworm.presenters

import android.support.design.chip.Chip
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.schedulers.Schedulers
import jose.com.bookworm.books.BaseApiTest
import jose.com.bookworm.books.successfulGetBestSellersListNamesResponse
import jose.com.bookworm.books.successfulGetBestSellersListResponse
import jose.com.bookworm.books.successfulGetTopFiveBestSellersResponse
import jose.com.bookworm.model.nytimes.BestSellersBook
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.presentations.FeedPresentation
import org.awaitility.Awaitility.await
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.TimeUnit.SECONDS

class FeedPresenterTest : BaseApiTest() {
    private lateinit var presenter: FeedPresenter
    private lateinit var presentation: FeedPresentation

    @Before
    override fun setup() {
        super.setup()

        presentation = mock()

        presenter = FeedPresenter(
            context,
            client,
            Schedulers.io(),
            Schedulers.io()
        )
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

        var booksLoaded = false

        presenter.getBestSellersOverview { booksLoaded = true }

        verify(presentation).showLoading()

        await()
            .atMost(2, SECONDS)
            .until { booksLoaded }

        verify(presentation).hideLoading()
        verify(presentation).showBestSellersList(listOf(bestSellerBook))
        verify(presentation).showGetBestSellersSuccess("")
        verifyNoMoreInteractions(presentation)
    }

    @Test
    fun `When FeedFragment is attached, best-sellers chip group is populated`() {
        mockWebServer.enqueue(successfulGetBestSellersListNamesResponse)

        presenter.attach(presentation)

        var listLoaded = false

        presenter.getBestSellersListNames { listLoaded = true }

        verify(presentation).showLoading()
        val chip = Mockito.mock(Chip::class.java)
        chip.text = "Combined Print & E-Book Fiction"

        whenever(presenter.createChips(mutableListOf("Combined Print & E-Book Fiction")))
            .thenReturn(mutableListOf(chip))

        await()
            .atMost(2, SECONDS)
            .until { listLoaded }

        verify(presentation).hideLoading()
        verify(presentation).loadListNamesChips(mutableListOf(chip))
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