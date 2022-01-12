package ru.alinadorozhkina.deezer_music.mvp.model.data

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import ru.alinadorozhkina.deezer_music.mvp.contract.RadioContract

import ru.alinadorozhkina.deezer_music.mvp.model.entities.*

class DataSourceRadio : RadioContract.DataSource {

    override fun getData(): Single<List<CategoryModel>> {
        return getListGenreData().flatMapIterable {
            it.take(15)
        }.flatMap { genre ->
            getTrackList(genre).toObservable().map {
                createDataModel(genre, it)
            }
        }.toList()
    }

    private fun getTrackList(genre: Genre): Single<List<Track>> =
        RetrofitService.api.getTrackList(genre.tracklist).map { it.data }

    private fun getListGenreData(): Observable<List<Genre>> =
        RetrofitService.api.getRadioData().map { it.data }

    private fun createDataModel(genre: Genre, tracks: List<Track>): CategoryModel {
        val model = CategoryModel(genre.title, tracks)
        Log.d("DataSourceRadio", model.toString())
        return model
    }
}
