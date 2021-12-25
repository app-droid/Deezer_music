package ru.alinadorozhkina.deezer_music.mvp.contract

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainContract {
    interface MainView: MvpView

    interface Presenter {
        fun onDestroy()
        fun onFirstViewAttach()
    }
}