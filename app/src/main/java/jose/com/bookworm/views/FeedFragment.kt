package jose.com.bookworm.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jose.com.bookworm.R
import jose.com.bookworm.adapter.GenericAdapter
import jose.com.bookworm.di.Injector
import jose.com.bookworm.extensions.toast
import jose.com.bookworm.model.nytimes.BestSellersOverviewBook
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.presentations.FeedPresentation
import jose.com.bookworm.presenters.FeedPresenter
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : Fragment(), FeedPresentation {
    @Inject
    lateinit var presenter: FeedPresenter
    private lateinit var bestSellersAdapter: GenericAdapter<BestSellersOverviewBook>
    private lateinit var currentReadingAdapter: GenericAdapter<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Injector.applicationComponent.inject(this)

        bestSellersAdapter = GenericAdapter(R.layout.best_seller_list_item)
        recommended_rv.adapter = bestSellersAdapter
    }

    override fun onStart() {
        super.onStart()

        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()

        presenter.detach()
    }

    override fun showNotReadingAnyBooksText() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNotReadingAnyBooksText() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRefreshing() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideRefreshing() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showBestSellersList(books: List<BestSellersOverviewBook>?) {
        bestSellersAdapter.data = books!!
    }

    override fun showGetBestSellersFailed() { activity?.toast("Failed to get best-sellers") }
}
