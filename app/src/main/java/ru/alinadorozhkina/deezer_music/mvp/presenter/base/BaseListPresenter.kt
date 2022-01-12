package ru.alinadorozhkina.deezer_music.mvp.presenter.base

import ru.alinadorozhkina.deezer_music.mvp.contract.AppStateEntity
import ru.alinadorozhkina.deezer_music.mvp.contract.IDataItemView
import ru.alinadorozhkina.deezer_music.mvp.contract.IListPresenter

abstract class BaseListPresenter<E : AppStateEntity>(val data: List<E>) :
    IListPresenter<E, IDataItemView<E>> {

    override var itemClickListener: ((IDataItemView<E>) -> Unit)? = null

    override fun getCount(): Int = data.size

    override fun bindView(view: IDataItemView<E>) {
        val unity = data[view.pos]
        view.bind(unity)
    }
}