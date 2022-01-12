package ru.alinadorozhkina.deezer_music.api.data

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Chart
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Radio
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList

interface ApiService {

    @GET("chart")
    fun getData(): Observable<Chart>

    @GET("radio/top")
    fun getRadioData(): Observable<Radio>

    @GET
    fun getTrackList(@Url url: String): Single<TrackList>

}