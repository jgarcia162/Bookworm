package jose.com.bookworm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jose.com.bookworm.presenters.BasePresenter

@Suppress("UNCHECKED_CAST")
abstract class GenericAdapter<T>(
    val presenter: BasePresenter,
    val layout: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: List<T> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
            , viewType)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as Binder<T>).bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    interface Binder<T>{
        fun bind(data: T)
    }
}
