package ru.alinadorozhkina.deezer_music.mvp.presenter.list

import ru.alinadorozhkina.deezer_music.mvp.contract.AppStateEntity
import ru.alinadorozhkina.deezer_music.mvp.contract.ChartContract
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Album
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Artist
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track

abstract class ChartListPresenter<E : AppStateEntity>(val data: List<E>) :
    ChartContract.IChartListPresenter<E> {

    override var itemClickListener: ((ChartContract.IChartItemView<E>) -> Unit)? = null

    override fun getCount(): Int = data.size

    override fun bindView(view: ChartContract.IChartItemView<E>) {
        val unity = data[view.pos]
        view.bind(unity)
    }
}

class TopTracksListPresenter(data: List<Track>) : ChartListPresenter<Track>(data)

class TopAlbumsListPresenter(data: List<Album>) : ChartListPresenter<Album>(data)

class TopArtistListPresenter(data: List<Artist>) : ChartListPresenter<Artist>(data)


