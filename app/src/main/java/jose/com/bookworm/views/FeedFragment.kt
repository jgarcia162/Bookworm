package jose.com.bookworm.views

import android.os.Bundle
import android.support.design.chip.Chip
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import jose.com.bookworm.R
import jose.com.bookworm.adapter.BestSellersViewHolder
import jose.com.bookworm.adapter.GenericAdapter
import jose.com.bookworm.di.Injector
import jose.com.bookworm.extensions.addChips
import jose.com.bookworm.extensions.toast
import jose.com.bookworm.model.nytimes.NYTimesBook
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.presentations.FeedPresentation
import jose.com.bookworm.presenters.FeedPresenter
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : Fragment(), FeedPresentation {
    @Inject
    lateinit var presenter: FeedPresenter
    private lateinit var bestSellersAdapter: GenericAdapter<NYTimesBook>
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

        bestSellersAdapter = object : GenericAdapter<NYTimesBook>() {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return BestSellersViewHolder(view)
            }

            override fun getLayoutId(position: Int, obj: NYTimesBook): Int {
                return R.layout.best_seller_list_item
            }
        }
        recommended_rv.setHasFixedSize(true)
        recommended_rv.adapter = bestSellersAdapter

        list_names_chips.setOnCheckedChangeListener { chipGroup, i ->
            val chip = chipGroup.findViewById<Chip>(i)
            if (chip != null){
                presenter.getBestSellersList(chip.chipText!!.toString())
            }
        }
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

    }

    override fun hideLoading() {

    }

    override fun loadListNamesChips(listTitles: MutableList<String>) {
        list_names_chips.addChips(listTitles)
    }

    override fun showGetBestSellersSuccess(listName: String) {
        best_sellers_chips_scroll_view.visibility = VISIBLE
        suggestions_tv.text = getString(R.string.best_sellers, listName)
    }

    override fun showGetBestSellersFailed() {
        activity?.toast(getString(R.string.best_sellers_failed))
        best_sellers_chips_scroll_view.visibility = GONE
        suggestions_tv.text = getString(R.string.best_sellers_failed)
    }

    override fun showBestSellersList(books: List<NYTimesBook>) {
        bestSellersAdapter.data = books
    }

    override fun showBestSellersListFailed() {

    }
}
