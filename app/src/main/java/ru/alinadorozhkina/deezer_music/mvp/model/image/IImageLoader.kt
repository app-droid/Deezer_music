package ru.alinadorozhkina.deezer_music.mvp.model.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}