package ru.alinadorozhkina.deezer_music.mvp.usecases

import io.reactivex.Observable
import ru.alinadorozhkina.deezer_music.mvp.contract.AppState
import ru.alinadorozhkina.deezer_music.mvp.contract.AppStateEntity
import ru.alinadorozhkina.deezer_music.mvp.contract.Interactor
import ru.alinadorozhkina.deezer_music.mvp.contract.Repository
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Chart

class MainInteractor(
    private val repository: Repository<Chart>
) : Interactor<AppState<Chart>> {
    override fun getData(): Observable<AppState<Chart>> {
        return repository.getData().map { AppState.Success(it) }
    }
}