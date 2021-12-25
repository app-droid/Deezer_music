package ru.alinadorozhkina.deezer_music.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.alinadorozhkina.deezer_music.mvp.contract.MainContract
import ru.alinadorozhkina.deezer_music.mvp.navigation.IScreens

class MainPresenter(
    val router: Router,
    val screens: IScreens
) : MainContract.Presenter, MvpPresenter<MainContract.MainView>() {

    override fun onFirstViewAttach() {
        router.navigateTo(screens.main())
        super.onFirstViewAttach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}