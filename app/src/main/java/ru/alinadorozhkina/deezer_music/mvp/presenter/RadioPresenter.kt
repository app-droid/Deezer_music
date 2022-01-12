package ru.alinadorozhkina.deezer_music.mvp.presenter

import android.util.Log
import io.reactivex.observers.DisposableSingleObserver
import moxy.InjectViewState
import ru.alinadorozhkina.deezer_music.mvp.contract.AppState
import ru.alinadorozhkina.deezer_music.mvp.contract.RadioContract
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Category
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BasePresenter
import ru.alinadorozhkina.deezer_music.mvp.usecases.RadioInteractor

@InjectViewState
class RadioPresenter(
    private val interactor: RadioInteractor = RadioInteractor()
) : BasePresenter<Category, RadioContract.View>(),
    RadioContract.Presenter {

    override fun onFirstViewAttach() {
        getCategoriesData()
    }

    override fun getCategoriesData() {
        compositeDisposable.add(
            interactor.getData()
                .subscribeOn(schedulerProvider.io)
                .observeOn(schedulerProvider.ui)
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableSingleObserver<AppState<Category>> {
        return object : DisposableSingleObserver<AppState<Category>>() {
            override fun onSuccess(t: AppState<Category>) {
                viewState.renderData(t)
                Log.d("RadioPresenter", t.toString())
            }

            override fun onError(e: Throwable) {
                viewState.renderData(AppState.Error(e))
                Log.d("RadioPresenter Error", e.toString())
            }
        }
    }
}