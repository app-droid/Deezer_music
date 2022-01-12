package ru.alinadorozhkina.deezer_music.mvp.presenter.list

import ru.alinadorozhkina.deezer_music.mvp.model.entities.Album
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Artist
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BaseListPresenter

class TopTracksListPresenter(data: List<Track>) : BaseListPresenter<Track>(data)

class TopAlbumsListPresenter(data: List<Album>) : BaseListPresenter<Album>(data)

class TopArtistListPresenter(data: List<Artist>) : BaseListPresenter<Artist>(data)


