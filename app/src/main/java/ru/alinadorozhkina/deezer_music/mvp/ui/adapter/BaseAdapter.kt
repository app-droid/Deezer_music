package ru.alinadorozhkina.deezer_music.mvp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alinadorozhkina.deezer_music.mvp.contract.AppStateEntity
import ru.alinadorozhkina.deezer_music.mvp.contract.ChartContract

class BaseAdapter<E : AppStateEntity>(
    private val presenter: ChartContract.IChartListPresenter<E>,
    private val itemLayoutId: Int,
    private val bind: ((View, data: E) -> Unit)
) : RecyclerView.Adapter<BaseAdapter<E>.BaseViewHolder>() {

    inner class BaseViewHolder(private val root: View) : RecyclerView.ViewHolder(root),
        ChartContract.IChartItemView<E> {

        override var pos = -1

        override fun bind(data: E) {
            bind(root, data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayoutId, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int = presenter.getCount()
}
