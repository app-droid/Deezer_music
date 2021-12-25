package ru.alinadorozhkina.deezer_music.mvp.model.data

import io.reactivex.Observable
import ru.alinadorozhkina.deezer_music.mvp.contract.DataSource
import ru.alinadorozhkina.deezer_music.mvp.contract.Repository

class RepositoryImplementation<T>(
    private val dataSource: DataSource<T>
    ) : Repository<T> {
    override fun getData(): Observable<T> {
        return dataSource.getData()
    }
}