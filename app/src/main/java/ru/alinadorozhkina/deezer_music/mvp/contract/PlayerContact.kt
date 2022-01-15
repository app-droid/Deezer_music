package ru.alinadorozhkina.deezer_music.mvp.contract

import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList

class PlayerContact {

    @AddToEndSingle
    interface View : IBaseView<TrackList> {
        fun setBackground(url: String)
        fun setArtistName(name: String)
        fun setTrackTitle(title: String)
        fun seekbarMax(duration: Int)
        fun seekbarProgress(progress: Int)
    }

    interface Presenter: IPresenter<TrackList, View> {
        fun playClicked()
    }
}