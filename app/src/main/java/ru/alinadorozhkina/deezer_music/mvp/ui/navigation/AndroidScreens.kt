package ru.alinadorozhkina.deezer_music.mvp.ui.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.navigation.IScreens
import ru.alinadorozhkina.deezer_music.mvp.ui.fragments.ChartFragment
import ru.alinadorozhkina.deezer_music.mvp.ui.fragments.FavouritesFragment
import ru.alinadorozhkina.deezer_music.mvp.ui.fragments.PlayerFragment
import ru.alinadorozhkina.deezer_music.mvp.ui.fragments.RadioFragment

class AndroidScreens : IScreens {

    override fun main(): Screen = FragmentScreen { ChartFragment.newInstance() }

    override fun radio(): Screen = FragmentScreen { RadioFragment.newInstance() }

    override fun favourites(): Screen = FragmentScreen { FavouritesFragment.newInstance() }

    override fun player(track: Track): Screen = FragmentScreen { PlayerFragment.newInstance(track) }

}