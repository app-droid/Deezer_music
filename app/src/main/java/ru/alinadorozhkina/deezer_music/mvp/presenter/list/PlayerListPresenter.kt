package ru.alinadorozhkina.deezer_music.mvp.presenter.list

import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BaseListPresenter

class PlayerListPresenter(data: List<Track>): BaseListPresenter<Track>(data)