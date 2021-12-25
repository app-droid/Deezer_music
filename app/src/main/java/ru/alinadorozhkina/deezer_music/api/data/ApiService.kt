package ru.alinadorozhkina.deezer_music.api.data

import io.reactivex.Observable
import retrofit2.http.GET
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Chart

interface ApiService {
    @GET("chart")
    fun getData(): Observable<Chart>

}