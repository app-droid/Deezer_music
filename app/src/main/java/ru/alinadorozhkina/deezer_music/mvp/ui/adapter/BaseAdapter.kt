package ru.alinadorozhkina.deezer_music.mvp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alinadorozhkina.deezer_music.mvp.contract.*

open class BaseAdapter<E : AppStateEntity>(
    private val presenter: IListPresenter<E, IDataItemView<E>>,
    private val itemLayoutId: Int,
    private val bind: ((View, data: E) -> Unit)
) : RecyclerView.Adapter<BaseAdapter<E>.BaseViewHolder>() {

    open inner class BaseViewHolder(private val root: View) : RecyclerView.ViewHolder(root),
        IDataItemView<E> {

        override var pos = -1

        override fun bind(data: E) {
            bind(root, data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayoutId, parent, false)
        return BaseViewHolder(view).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int = presenter.getCount()
}

//class ChildAdapter(
//    private val presenter: ChildRadioListPresenter,
//    private val itemLayoutId: Int,
//    private val bind: ((View, data: Track) -> Unit)
//): BaseAdapter<Track>(presenter, itemLayoutId, bind)
//
//class ParentAdapter(
//    private val presenter: RadioListPresenter,
//    private val itemLayoutId: Int,
//    private val bind: ((View, data: Genre) -> Unit)
//): BaseAdapter<Genre>(presenter, itemLayoutId, bind) {
//    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//
//    }


