package ru.alinadorozhkina.deezer_music.mvp.model.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.alinadorozhkina.deezer_music.api.data.ApiService
import ru.alinadorozhkina.deezer_music.mvp.contract.DataSource
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Chart

class RetrofitImplementation: DataSource<Chart> {
    override fun getData(): Observable<Chart> {
        return createRetrofit().create<ApiService>().getData()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://api.deezer.com/"
    }
}