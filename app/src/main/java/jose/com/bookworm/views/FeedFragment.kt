package jose.com.bookworm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import jose.com.bookworm.R
import jose.com.bookworm.adapter.GenericAdapter
import jose.com.bookworm.di.Injector
import jose.com.bookworm.extensions.onClick
import jose.com.bookworm.extensions.toast
import jose.com.bookworm.model.nytimes.NYTimesBook
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.presentations.FeedPresentation
import jose.com.bookworm.presenters.FeedPresenter
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : Fragment(), FeedPresentation, View.OnClickListener {
    @Inject
    lateinit var presenter: FeedPresenter
    private lateinit var bestSellersAdapter: GenericAdapter<NYTimesBook>
    private lateinit var currentReadingAdapter: GenericAdapter<Book>
    private lateinit var categoryTitles: Set<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.applicationComponent.inject(this)

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
        bestSellersAdapter = GenericAdapter(R.layout.best_seller_list_item)

        best_sellers_rv.setHasFixedSize(true)
        best_sellers_rv.adapter = bestSellersAdapter

        currentReadingAdapter = GenericAdapter(R.layout.current_reading_list_item)

        current_reading_rv.adapter = currentReadingAdapter

        filter_icon.onClick {
            //TODO re-inflate fragment if created
            ChipsDialogFragment()
                .apply {
                    listener = this@FeedFragment
                    chipTitles = categoryTitles
                }
                .show(childFragmentManager.beginTransaction(), "categories_fragment")
        }
    }

    override fun onStart() {
        super.onStart()

        presenter.attach(this)

        presenter.getBestSellersListNames()
        presenter.getBestSellersOverview()
        presenter.getCurrentReadings()
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

    override fun loadListNamesChips(listTitles: MutableSet<String>) {
        categoryTitles = listTitles
    }

    override fun onClick(v: View?) {
        val chip = v as Chip
        presenter.getBestSellersList(chip.text!!.toString())
    }

    override fun showGetBestSellersSuccess(listName: String) {
        suggestions_tv.text = getString(R.string.best_sellers, listName)
    }

    override fun showGetBestSellersFailed() {
        activity?.toast(getString(R.string.best_sellers_failed))
        suggestions_tv.text = getString(R.string.best_sellers_failed)
    }

    override fun showBestSellersList(books: List<NYTimesBook>) {
        bestSellersAdapter.data = books
    }

    override fun getBestSellerList(listName: String) {
        presenter.getBestSellersList(listName)
    }

    override fun getMultipleLists(listNames: Set<String>) {
        presenter.getMultipleLists(listNames)
    }

    override fun getOverviewList() {
        presenter.getBestSellersOverview()
    }

    override fun showNoResults() {
        activity?.toast(getString(R.string.no_results))
    }

    override fun showBestSellersListFailed(listName: String) {
        activity?.toast("Couldn't load books for list $listName")
    }
}
