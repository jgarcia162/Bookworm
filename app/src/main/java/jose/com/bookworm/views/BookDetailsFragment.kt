package jose.com.bookworm.views

import android.support.v4.app.Fragment
import jose.com.bookworm.model.Book
import jose.com.bookworm.presentations.BookDetailsPresentation
import jose.com.bookworm.presenters.BookDetailsPresenter

class BookDetailsFragment : Fragment(), BookDetailsPresentation {
    lateinit var presenter: BookDetailsPresenter

    override fun onStart() {
        super.onStart()

        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()

        presenter.detach()
    }

    override fun showBookDeleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showBookCheckedOut() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showBookCheckedIn() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEditBookLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideEditBookLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showBookDetails() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateBookData(book: Book) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}