package ru.alinadorozhkina.deezer_music.mvp.usecases

import android.util.Log
import io.reactivex.Single
import ru.alinadorozhkina.deezer_music.mvp.contract.AppState
import ru.alinadorozhkina.deezer_music.mvp.contract.RadioContract
import ru.alinadorozhkina.deezer_music.mvp.model.data.RadioRepository
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Category

class RadioInteractor(
    private val repoRadio: RadioContract.Repository = RadioRepository(),
) : RadioContract.Interactor<AppState<Category>> {
    override fun getData(): Single<AppState<Category>> {
        return repoRadio.getData().map {
            Log.d("RadioPresenter", it.toString())
            AppState.Success(it)
        }
    }
}



