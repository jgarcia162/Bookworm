package jose.com.bookworm.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import jose.com.bookworm.R
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.presentations.BookDetailsPresentation
import jose.com.bookworm.presenters.BookDetailsPresenter

class BookDetailsFragment : Fragment(), BookDetailsPresentation {
    lateinit var presenter: BookDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

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

    override fun updateProgressSeekBar(){
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.details_edit_book_menu_option -> showEditBookLayout()
        }
        return true
    }
}