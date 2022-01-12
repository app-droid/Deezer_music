package ru.alinadorozhkina.deezer_music.mvp.presenter.list

import ru.alinadorozhkina.deezer_music.mvp.model.entities.CategoryModel
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BaseListPresenter

class RadioListPresenter(
    data: List<CategoryModel>
) : BaseListPresenter<CategoryModel>(data)

class ChildRadioListPresenter(
    data: List<Track>
) : BaseListPresenter<Track>(data)

