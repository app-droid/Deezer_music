package ru.alinadorozhkina.deezer_music.mvp.model.audio

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface IAudioPlayer {
    fun play(url: String): Completable
    fun pause()
    fun getDuration(): Single<Int>
    fun progress(): Observable<Int>
    fun release()
    fun seekTo(msec: Int)
    fun getCurrentPosition(): Int
    fun isPlaying(): Boolean
    fun reset()
}