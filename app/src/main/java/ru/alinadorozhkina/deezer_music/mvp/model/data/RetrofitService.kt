package ru.alinadorozhkina.deezer_music.mvp.model.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.alinadorozhkina.deezer_music.api.data.ApiService

object RetrofitService {
    private const val BASE_URL = "https://api.deezer.com/"

    private fun retrofitService(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val api: ApiService by lazy {
        retrofitService().create(ApiService::class.java)
    }

}