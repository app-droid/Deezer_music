package ru.alinadorozhkina.deezer_music.mvp.contract

import io.reactivex.Observable
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AppStateEntity

sealed interface AppState<out T : AppStateEntity> {
    data class Success<out T : AppStateEntity>(val data: T) : AppState<T>
    data class Error<out T : AppStateEntity>(val error: Throwable) : AppState<T>
    //data class Loading<out T : AppStateEntity>(val isLoading: Boolean) : AppState<T>
}

interface IBaseView<E : AppStateEntity> : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderData(baseState: AppState<E>)
}

interface IPresenter<E : AppStateEntity, V : IBaseView<E>> {
    fun onDestroy()
    fun onFirstViewAttach()
}


interface Interactor<T : AppStateEntity> {
    fun getData(): Observable<AppState<T>>
}

interface Repository<T> {

    fun getData(): Observable<T>
}

// Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSource<T> {

    fun getData(): Observable<T>
}

interface IItemView {
    var pos: Int
}

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?

    fun bindView(view: V)
    fun getCount(): Int
}


//interface View : MvpView {
//    // View имеет только один метод, в который приходит некое состояние приложения
//    fun renderData(baseState: AppState)
//}
//
//interface ItemView: MvpView {
//    var pos: Int
//}

// На уровень выше находится презентер, который уже ничего не знает ни о контексте, ни о фреймворке
//abstract class Presenter<T : AppState, V : View>: MvpPresenter<V>() {
//    abstract override fun attachView(view: V)
//    abstract override fun detachView(view: V)
//    abstract fun getData()
//}

//interface Presenter<T : AppState, V : View>{
//    fun attachView(view: V)
//
//    fun detachView(view: V)
//
//    fun getData()

//interface IListPresenter<V: ItemView> {
//    var itemClickListener: ((V) -> Unit)?
//
//    fun bindView(view: V)
//    fun getCount(): Int
//}
//

// Ещё выше стоит интерактор. Здесь уже чистая бизнес-логика

