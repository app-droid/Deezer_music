package ru.alinadorozhkina.deezer_music.mvp.presenter

import io.reactivex.observers.DisposableObserver
import moxy.InjectViewState
import ru.alinadorozhkina.deezer_music.mvp.model.data.DataSourceRemote
import ru.alinadorozhkina.deezer_music.mvp.model.data.RepositoryImplementation
import ru.alinadorozhkina.deezer_music.mvp.contract.AppState
import ru.alinadorozhkina.deezer_music.mvp.contract.ChartContract
import ru.alinadorozhkina.deezer_music.mvp.contract.Interactor
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BasePresenter
import ru.alinadorozhkina.deezer_music.mvp.usecases.MainInteractor
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Chart

@InjectViewState
class ChartPresenter(
    private val interactor: Interactor<Chart> = MainInteractor(
        RepositoryImplementation(
            DataSourceRemote()
        )
    )
) : BasePresenter<Chart, ChartContract.View<Chart>>(),
    ChartContract.Presenter<Chart> {

    override fun onFirstViewAttach() {
        getChartData()
    }

    override fun getChartData() {
        compositeDisposable.add(
            interactor.getData()
                .subscribeOn(schedulerProvider.io)
                .observeOn(schedulerProvider.ui)
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState<Chart>> {
        return object : DisposableObserver<AppState<Chart>>() {

            override fun onNext(appState: AppState<Chart>) {
                viewState.renderData(appState)
            }

            override fun onError(e: Throwable) {
                viewState.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}