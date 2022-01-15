package ru.alinadorozhkina.deezer_music.mvp.ui.audio

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.core.view.inputmethod.InputConnectionCompat
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.alinadorozhkina.deezer_music.mvp.model.audio.IAudioPlayer

class AndroidMediaPlayer : IAudioPlayer {

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }

    val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / 1000
        }

    private val listener = MediaPlayer.OnCompletionListener { mediaPlayer.reset() }

    init {
        mediaPlayer.setOnCompletionListener(listener)
    }

    override fun play(url: String) = Completable.create { emitter ->
        try {
            mediaPlayer.apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(url)
                prepare()
                start()
                emitter.onComplete()
            }
        } catch (t: Throwable) {
            emitter.onError(t)
        }
    }.subscribeOn(Schedulers.io())

    override fun pause() {
        mediaPlayer.pause()
    }

    override fun getDuration() = Single.fromCallable {
        mediaPlayer.seconds
    }.subscribeOn(Schedulers.io())

    override fun progress(): Observable<Int> = Observable.create { emitter ->
        while (mediaPlayer.isPlaying) {
            Thread.sleep(1000)
            emitter.onNext(mediaPlayer.currentSeconds)
        }
    }

    override fun isPlaying(): Boolean = mediaPlayer.isPlaying

    override fun getCurrentPosition(): Int = mediaPlayer.currentPosition

    override fun seekTo(msec: Int) {
        mediaPlayer.seekTo(msec)
        mediaPlayer.start()
    }

    override fun release() {
        mediaPlayer.release()
    }

}