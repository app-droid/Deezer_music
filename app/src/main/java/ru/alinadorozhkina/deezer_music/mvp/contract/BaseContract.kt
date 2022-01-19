package ru.alinadorozhkina.deezer_music.mvp.contract

import io.reactivex.Observable
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AppStateEntity

interface AppState<out T : AppStateEntity> {
    data class Success<out T : AppStateEntity>(val data: T) : AppState<T>
    data class Error<out T : AppStateEntity>(val error: Throwable) : AppState<T>
    //data class Loading<out T : AppStateEntity>(val isLoading: Boolean) : AppState<T>
}

@StateStrategyType(AddToEndSingleStrategy::class)
interface IBaseView<E: AppStateEntity> : MvpView {
    fun renderData(baseState: AppState<E>)
}

interface IPresenter<E: AppStateEntity, V : IBaseView<E>> {
    fun onDestroy()
    fun onFirstViewAttach()
}

interface Interactor<T> {
    fun getData(): Observable<T>
}

interface Repository<T> {

    fun getData(): Observable<T>
}

interface DataSource <T> {
    fun getData(): Observable<T>
}

interface IItemView {
    var pos: Int
}

interface IDataItemView<E : AppStateEntity> : IItemView {
        fun bind(data: E)
    }

interface IListPresenter<E : AppStateEntity, V : IDataItemView<E>> {
    var itemClickListener: ((V) -> Unit)?

    fun bindView(view: V)
    fun getCount(): Int
}
