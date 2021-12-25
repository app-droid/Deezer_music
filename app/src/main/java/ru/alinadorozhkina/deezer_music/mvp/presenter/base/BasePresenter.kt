package ru.alinadorozhkina.deezer_music.mvp.presenter.base

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.alinadorozhkina.deezer_music.mvp.contract.AppStateEntity
import ru.alinadorozhkina.deezer_music.mvp.contract.IBaseView
import ru.alinadorozhkina.deezer_music.mvp.contract.IPresenter
import ru.alinadorozhkina.deezer_music.rx.SchedulerProvider

abstract class BasePresenter<E : AppStateEntity, V : IBaseView<E>>
    : IPresenter<E, V>, MvpPresenter<V>() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    abstract override fun onFirstViewAttach()
}