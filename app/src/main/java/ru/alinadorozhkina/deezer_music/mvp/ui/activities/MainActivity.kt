package ru.alinadorozhkina.deezer_music.mvp.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.alinadorozhkina.deezer_music.App
import ru.alinadorozhkina.deezer_music.R
import ru.alinadorozhkina.deezer_music.databinding.ActivityMainBinding
import ru.alinadorozhkina.deezer_music.mvp.contract.MainContract
import ru.alinadorozhkina.deezer_music.mvp.presenter.MainPresenter
import ru.alinadorozhkina.deezer_music.mvp.ui.navigation.AndroidScreens

class MainActivity : MvpAppCompatActivity(), MainContract.MainView {

    private var vb: ActivityMainBinding? = null

    val navigator = AppNavigator(this, R.id.container)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = MainPresenter(App.INSTANCE.router, AndroidScreens())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        setSupportActionBar(vb?.toolbar)
        initNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            Toast.makeText(this, "Print action", Toast.LENGTH_LONG).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun initNavigation() {
        vb?.bottomNavigation?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main -> presenter.toMain()
                R.id.radio -> presenter.toRadio()
                R.id.favourites -> presenter.toFavourites()
            }
            true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }
}