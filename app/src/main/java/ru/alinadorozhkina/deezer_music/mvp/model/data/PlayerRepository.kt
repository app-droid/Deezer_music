package ru.alinadorozhkina.deezer_music.mvp.model.data

import android.util.Log
import io.reactivex.Single
import ru.alinadorozhkina.deezer_music.mvp.contract.PlayerContact
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList

class PlayerRepository(
    private val dataSource: PlayerContact.DataSource = DataSourcePlayer()
) : PlayerContact.Repository {
    override fun getData(url: String): Single<TrackList> {
       return dataSource.getData(url)
    }

    override fun getData2(url: String): Single<List<Track>> {
       return dataSource.getData2(url)
    }
}