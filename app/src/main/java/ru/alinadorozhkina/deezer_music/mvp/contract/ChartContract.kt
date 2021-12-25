package ru.alinadorozhkina.deezer_music.mvp.contract

interface ChartContract {

    interface View<E : AppStateEntity> : IBaseView<E>

    interface Presenter<E : AppStateEntity> : IPresenter<E, View<E>> {
        fun getChartData()
    }

    interface IChartItemView<E : AppStateEntity> : IItemView {
        fun bind(data: E)
    }

    interface IChartListPresenter<E : AppStateEntity> : IListPresenter<IChartItemView<E>>

    interface BaseClickListener

}
