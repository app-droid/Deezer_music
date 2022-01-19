package ru.alinadorozhkina.deezer_music.mvp.presenter

import android.util.Log
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import moxy.InjectViewState
import ru.alinadorozhkina.deezer_music.mvp.contract.AppState
import ru.alinadorozhkina.deezer_music.mvp.contract.IDataItemView
import ru.alinadorozhkina.deezer_music.mvp.contract.PlayerContact
import ru.alinadorozhkina.deezer_music.mvp.model.audio.IAudioPlayer
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BaseListPresenter
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BasePresenter
import ru.alinadorozhkina.deezer_music.mvp.ui.audio.AndroidMediaPlayer
import ru.alinadorozhkina.deezer_music.mvp.usecases.PlayerInteractor

@InjectViewState
class PlayerPresenter(
    private val interactor: PlayerContact.Interactor = PlayerInteractor()
) : BasePresenter<TrackList, PlayerContact.View>(),
    PlayerContact.Presenter {

    private val player: IAudioPlayer = AndroidMediaPlayer()

    var playerTrack2: PublishSubject<Track> = PublishSubject.create()

    override fun init(track: Track) {
        viewState.setBackground(track.artist.picture)
        viewState.setArtistName(track.artist.name)
        viewState.setTrackTitle(track.title)
        play(track.preview)
        getTrackList(track)
    }

    override fun onFirstViewAttach() {
        compositeDisposable.add(
            playerTrack2.subscribeWith(getTrackObserver())
        )
    }

    private fun getTrackObserver(): DisposableObserver<Track> {
        return object : DisposableObserver<Track>() {
            override fun onNext(t: Track) {
                viewState.setBackground(t.album.cover)
                viewState.setTrackTitle(t.title)
                player.reset()
                play(t.preview)
            }

            override fun onError(e: Throwable) {
                player.release()
            }

            override fun onComplete() {
                player.release()
            }
        }
    }

    override fun playClicked() {
        player.seekTo(player.getCurrentPosition())
        compositeDisposable.add(
            player.progress()
                .subscribeOn(schedulerProvider.io)
                .observeOn(schedulerProvider.ui)
                .subscribeWith(getProgressObserver())
        )
    }

    override fun pauseClicked() {
        player.pause()
    }


    override fun getTrackList(track: Track) {
        val url = track.artist.tracklist
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

    private fun play(preview: String) {
        compositeDisposable.add(
            player.play(preview)
                .observeOn(schedulerProvider.ui)
                .subscribe({
                    compositeDisposable.add(player.getDuration().subscribe { duration ->
                        Log.d("PlayerPresenter getDuration()", duration.toString())
                        viewState.seekbarMax(duration)
                        compositeDisposable.add(
                            player.progress().subscribeWith(getProgressObserver())
                        )
                    })
                }, {
                    viewState.seekbarMax(0)
                })
        )
    }

    private fun getProgressObserver(): DisposableObserver<Int> {
        return object : DisposableObserver<Int>() {
            override fun onNext(t: Int) {
                viewState.seekbarProgress(t)
            }

            override fun onError(e: Throwable) {
                viewState.seekbarProgress(0)
                player.reset()
            }

            override fun onComplete() {
                Log.d("PlayerPresenter onComplete()", "трек завершился")
                // здесь буду менять на другой трек
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        playerTrack2.onComplete()
    }

    inner class PlayListPresenter(data: List<Track>) : BaseListPresenter<Track>(data) {

        override var itemClickListener: ((IDataItemView<Track>) -> Unit)? = { itemView ->
            val track = data[itemView.pos]
            Log.d("PlayerListPresenter", track.toString())
            playerTrack2.onNext(track)
        }
    }
}