package jose.com.bookworm.presenters

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import jose.com.bookworm.books.BaseApiTest
import jose.com.bookworm.books.successfulGetTopFiveBestSellersResponse
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.presentations.FeedPresentation
import org.awaitility.Awaitility.await
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit
class FeedPresenterTest: BaseApiTest() {
    private lateinit var presenter: FeedPresenter

    @Before
    override fun setup(){
        super.setup()

        presenter = FeedPresenter(
            apiClient = client
        )
    }

    @Test
    fun `When presenter is attached, it successfully shows best sellers`() {
        mockWebServer.enqueue(successfulGetTopFiveBestSellersResponse)

        val presentation: FeedPresentation = mock()

        val bestSellerBook = BestSellersOverviewBook(
            amazonUrl = "https://www.amazon.com/Past-Tense-Jack-Reacher-Novel/dp/0399593519?tag=NYTBS-20",
            author = "Lee Child",
            bookImage = "https://s1.nyt.com/du/books/images/9780399593512.jpg",
            description = "Jack Reacher explores the New England town where his father was born and a Canadian couple now find themselves stranded.",
            isbn10 = "",
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
            .atMost(2, TimeUnit.SECONDS)
            .until{ booksLoaded }

        verify(presentation).hideLoading()
        verify(presentation).showBestSellersList(listOf(bestSellerBook))
        verifyNoMoreInteractions(presentation)

    }
}