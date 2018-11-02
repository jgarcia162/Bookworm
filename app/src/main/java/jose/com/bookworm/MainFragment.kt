package jose.com.bookworm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainFragment: Fragment(){

  private val BID_KEY = "bid"
  private var viewModel = BookViewModel()

  private lateinit var titleTextView: TextView
  private lateinit var authorTextView: TextView

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel.getBook().observe(this, Observer{ book ->
      run {
        titleTextView.text = book?.title
        authorTextView.text = book?.author
      }
    })

    val bookId = arguments?.getLong(BID_KEY, -1)
    viewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
    viewModel?.init(bookId!!)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return super.onCreateView(inflater, container, savedInstanceState)
  }


  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
  }
}
