package ru.alinadorozhkina.deezer_music.mvp.presenter

import android.util.Log
import io.reactivex.observers.DisposableObserver
import moxy.InjectViewState
import ru.alinadorozhkina.deezer_music.mvp.contract.PlayerContact
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BasePresenter
import ru.alinadorozhkina.deezer_music.mvp.ui.audio.AndroidMediaPlayer

@InjectViewState
class PlayerPresenter(val track: Track) : BasePresenter<TrackList, PlayerContact.View>(),
    PlayerContact.Presenter {

    private val player = AndroidMediaPlayer()

    override fun onFirstViewAttach() {
        viewState.setBackground(track.artist.picture)
        viewState.setArtistName(track.artist.name)
        viewState.setTrackTitle(track.title)
    }

    override fun playClicked() {

        Log.d("PlayerPresenter", track.preview)
        if (player.isPlaying()) {
            Log.d("PlayerPresenter", "player is playing")
            player.pause()
        } else if (player.getCurrentPosition() > 1000) {
            Log.d("PlayerPresenter", "else if ${player.getCurrentPosition()}")
            player.seekTo(player.getCurrentPosition())
            compositeDisposable.add(
                player.progress()
                    .subscribeOn(schedulerProvider.io)
                    .observeOn(schedulerProvider.ui)
                    .subscribeWith(getObserver()))

        } else {
            Log.d("PlayerPresenter", "else")
            compositeDisposable.add(

                player.play(track.preview)
                    .subscribeOn(schedulerProvider.io)
                    .observeOn(schedulerProvider.ui)
                    .subscribe({
                        player.getDuration().subscribe { duration ->
                            Log.d("PlayerPresenter getDuration()", duration.toString())
                            viewState.seekbarMax(duration)
                            player.progress().subscribeWith(getObserver())
                        }
                    }, {
                        viewState.seekbarMax(0)
                    })
            )
        }
    }

    private fun getObserver(): DisposableObserver<Int> {
        return object : DisposableObserver<Int>() {
            override fun onNext(t: Int) {
                viewState.seekbarProgress(t)
            }

            override fun onError(e: Throwable) {
                viewState.seekbarProgress(0)
            }

            override fun onComplete() = Unit
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}