package ru.alinadorozhkina.deezer_music.mvp.navigation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun main(): Screen
    fun radio(): Screen
    fun favourites(): Screen
}