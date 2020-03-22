package jose.com.bookworm.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import jose.com.bookworm.di.Injector
import jose.com.bookworm.model.roommodel.Book
import jose.com.bookworm.viewmodel.SearchViewModel
import javax.inject.Inject

class SearchFragment() : DaggerFragment(){
    @Inject
    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.applicationComponent.inject(this)
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
    }

    override fun onStop() {
        super.onStop()
    }

    fun showNoResults() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun updateRV(results: List<Book>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showSearchForBookText() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun hideSearchForBookText() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}