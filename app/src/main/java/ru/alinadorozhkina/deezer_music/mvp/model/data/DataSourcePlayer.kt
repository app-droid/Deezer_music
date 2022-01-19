package ru.alinadorozhkina.deezer_music.mvp.model.data

import io.reactivex.Single
import ru.alinadorozhkina.deezer_music.mvp.contract.PlayerContact
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList

class DataSourcePlayer: PlayerContact.DataSource {
    override fun getData(url: String): Single<TrackList> {
        return RetrofitService.api.getTrackList(url)
    }

    override fun getData2(url: String): Single<List<Track>> =
        RetrofitService.api.getTrackList(url).map { it.data.take(10) }
}