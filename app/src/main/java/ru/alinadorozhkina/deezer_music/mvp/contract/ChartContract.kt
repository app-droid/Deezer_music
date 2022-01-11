package ru.alinadorozhkina.deezer_music.mvp.contract

import io.reactivex.Observable
import io.reactivex.Single
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Chart
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Genre
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track

interface ChartContract {

    interface View : IBaseView<Chart>

    interface Presenter: IPresenter<Chart, View> {
        fun getChartData()
    }

    interface Repository<T> {

        fun getData(): Observable<T>
    }

    interface BaseClickListener

}
