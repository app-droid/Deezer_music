package ru.alinadorozhkina.deezer_music.mvp.presenter.list

import android.util.Log
import com.github.terrakok.cicerone.Router
import ru.alinadorozhkina.deezer_music.mvp.contract.IDataItemView
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Album
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Artist
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.navigation.IScreens
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BaseListPresenter

class TopTracksListPresenter(router: Router, val screens: IScreens, data: List<Track>) : BaseListPresenter<Track>(data) {

    override var itemClickListener: ((IDataItemView<Track>) -> Unit)? = { itemView ->
        val track = data[itemView.pos]
        Log.d("TopTracksListPresenter", track.toString())
        router.navigateTo(screens.player(track))
    }
}

class TopAlbumsListPresenter(data: List<Album>) : BaseListPresenter<Album>(data)

class TopArtistListPresenter(data: List<Artist>) : BaseListPresenter<Artist>(data)


