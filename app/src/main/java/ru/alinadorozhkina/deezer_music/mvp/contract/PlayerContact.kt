package ru.alinadorozhkina.deezer_music.mvp.contract

import io.reactivex.Single
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList

interface PlayerContact {

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface View : IBaseView<TrackList> {
        fun setBackground(url: String)
        fun setArtistName(name: String)
        fun setTrackTitle(title: String)
        fun seekbarMax(duration: Int)
        fun seekbarProgress(progress: Int)
    }

    interface Presenter: IPresenter<TrackList, View> {
        fun playClicked()
        fun pauseClicked()
        fun getTrackList(track: Track)
        fun init(track: Track)
    }

    interface Repository {

        fun getData(url: String): Single<TrackList>
        fun getData2(url: String): Single<List<Track>>
    }

    interface DataSource {
        fun getData(url: String): Single<TrackList>
        fun getData2(url: String): Single<List<Track>>
    }

    interface Interactor{
        fun getData(url: String): Single<AppState<TrackList>>
        fun getData2(url: String): Single<AppState<TrackList>>
    }
}