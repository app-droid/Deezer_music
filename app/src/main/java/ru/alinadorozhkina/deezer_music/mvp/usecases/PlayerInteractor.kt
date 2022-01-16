package ru.alinadorozhkina.deezer_music.mvp.usecases

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import ru.alinadorozhkina.deezer_music.mvp.contract.AppState
import ru.alinadorozhkina.deezer_music.mvp.contract.PlayerContact
import ru.alinadorozhkina.deezer_music.mvp.model.data.PlayerRepository
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList

class PlayerInteractor(
    private val repository: PlayerContact.Repository = PlayerRepository()
) : PlayerContact.Interactor {
    override fun getData(url: String): Single<AppState<TrackList>> {
        return repository.getData(url).map {
            Log.d("PlayerInteractor", it.toString())
            AppState.Success(it) }
    }

    override fun getData2(url: String): Single<AppState<TrackList>> {
        return repository.getData2(url).map {
            Log.d("PlayerInteractor", it.toString())
            AppState.Success(TrackList(it)) }
    }
}