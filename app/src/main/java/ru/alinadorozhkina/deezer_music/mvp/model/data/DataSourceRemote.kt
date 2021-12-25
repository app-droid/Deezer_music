package ru.alinadorozhkina.deezer_music.mvp.model.data

import io.reactivex.Observable
import ru.alinadorozhkina.deezer_music.mvp.contract.DataSource
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Chart

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<Chart> {
    override fun getData(): Observable<Chart> {
        return remoteProvider.getData()
    }
}