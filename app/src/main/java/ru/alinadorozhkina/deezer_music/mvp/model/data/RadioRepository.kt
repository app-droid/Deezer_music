package ru.alinadorozhkina.deezer_music.mvp.model.data

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import ru.alinadorozhkina.deezer_music.mvp.contract.RadioContract
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Category
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Genre
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track

class RadioRepository(
    private val dataSource: DataSourceRadio = DataSourceRadio()
): RadioContract.Repository {

    override fun getData(): Single<Category> {
        return dataSource.getData().map {
            Log.d("RadioRepository", it.size.toString())
            Category(it)
        }
    }
}