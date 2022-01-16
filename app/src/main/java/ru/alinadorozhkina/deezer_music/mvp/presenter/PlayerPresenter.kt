package ru.alinadorozhkina.deezer_music.mvp.presenter

import android.util.Log
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import moxy.InjectViewState
import ru.alinadorozhkina.deezer_music.App
import ru.alinadorozhkina.deezer_music.mvp.contract.AppState
import ru.alinadorozhkina.deezer_music.mvp.contract.PlayerContact
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BasePresenter
import ru.alinadorozhkina.deezer_music.mvp.ui.audio.AndroidMediaPlayer
import ru.alinadorozhkina.deezer_music.mvp.usecases.PlayerInteractor

@InjectViewState
class PlayerPresenter(
    val track: Track,
    private val interactor: PlayerContact.Interactor = PlayerInteractor()
) : BasePresenter<TrackList, PlayerContact.View>(),
    PlayerContact.Presenter {

    private val player = AndroidMediaPlayer()

    override fun onFirstViewAttach() {
        getTrackList()
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
                    .subscribeWith(getObserver())
            )

        } else {
            Log.d("PlayerPresenter", "else")
            compositeDisposable.add(
                player.play(track.preview)
                    .observeOn(schedulerProvider.ui)
                    .subscribe({
                        compositeDisposable.add(player.getDuration().subscribe { duration ->
                            Log.d("PlayerPresenter getDuration()", duration.toString())
                            viewState.seekbarMax(duration)
                            compositeDisposable.add(player.progress().subscribeWith(getObserver()))
                        })
                    }, {
                        viewState.seekbarMax(0)
                    })
            )
        }
    }

    override fun getTrackList() {
        val url  = track.artist.tracklist
        Log.d("PlayerPresenter", url)
        compositeDisposable.add(
            interactor.getData(url)
                .subscribeOn(schedulerProvider.io)
                .observeOn(schedulerProvider.ui)
                .subscribeWith(getTracklistObserver())
        )
    }


    private fun getTracklistObserver(): DisposableSingleObserver<AppState<TrackList>> {
        return object : DisposableSingleObserver<AppState<TrackList>>() {
            override fun onSuccess(t: AppState<TrackList>) {
                Log.d("PlayerPresenter getTracklistObserver", t.toString())
                viewState.renderData(t)
            }

            override fun onError(e: Throwable) {
                viewState.renderData(AppState.Error(e))
            }
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