package ru.alinadorozhkina.deezer_music.mvp.model.data

import io.reactivex.Single
import ru.alinadorozhkina.deezer_music.mvp.contract.RadioContract
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Category

class RadioRepository(
    private val dataSource: DataSourceRadio = DataSourceRadio()
) : RadioContract.Repository {
    override fun getData(): Single<Category> =
        dataSource.getData().map { Category(it) }
}