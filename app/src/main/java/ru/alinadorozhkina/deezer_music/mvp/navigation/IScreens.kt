package ru.alinadorozhkina.deezer_music.mvp.navigation

import com.github.terrakok.cicerone.Screen
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track

interface IScreens {
    fun main(): Screen
    fun radio(): Screen
    fun favourites(): Screen
    fun player(track: Track): Screen
}