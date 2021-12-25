package ru.alinadorozhkina.deezer_music.mvp.usecases

import io.reactivex.Observable
import ru.alinadorozhkina.deezer_music.mvp.contract.AppState
import ru.alinadorozhkina.deezer_music.mvp.contract.AppStateEntity
import ru.alinadorozhkina.deezer_music.mvp.contract.Interactor
import ru.alinadorozhkina.deezer_music.mvp.contract.Repository

class MainInteractor<E: AppStateEntity>(
    private val repository: Repository<E>
) : Interactor<E> {
    override fun getData(): Observable<AppState<E>> {
        return repository.getData().map { AppState.Success(it) }
    }
}