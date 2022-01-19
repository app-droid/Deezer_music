package ru.alinadorozhkina.deezer_music.mvp.contract

import ru.alinadorozhkina.deezer_music.mvp.model.entities.Chart

interface ChartContract {

    interface View : IBaseView<Chart>

    interface Presenter: IPresenter<Chart, View> {
        fun getChartData()
    }

    interface BaseClickListener

}
