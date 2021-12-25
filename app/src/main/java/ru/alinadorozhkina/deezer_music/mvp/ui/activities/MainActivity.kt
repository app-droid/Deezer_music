package ru.alinadorozhkina.deezer_music.mvp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import ru.alinadorozhkina.deezer_music.R
import ru.alinadorozhkina.deezer_music.databinding.ActivityMainBinding
import ru.alinadorozhkina.deezer_music.mvp.ui.fragments.ChartFragment

class MainActivity : AppCompatActivity() {

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        setSupportActionBar(vb?.toolbar)
        initNavigation()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, ChartFragment.newInstance()) // or replace с теми же параметрами
            .addToBackStack(null) // если необходимо, чтоб по нажатию "назад" вы могли вернуться на предыдущий фрагмент. Вместо null можно задать свой ключ.
            .commit();
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
                R.id.main -> Toast.makeText(this, "home", Toast.LENGTH_LONG).show()
                R.id.radio -> Toast.makeText(this, "radio", Toast.LENGTH_LONG).show()
                R.id.favourites -> Toast.makeText(this, "favorites", Toast.LENGTH_LONG).show()
            }
            true
        }
    }
}