package ru.alinadorozhkina.deezer_music.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import moxy.presenter.InjectPresenter
import ru.alinadorozhkina.deezer_music.App
import ru.alinadorozhkina.deezer_music.R
import ru.alinadorozhkina.deezer_music.mvp.contract.ChartContract
import ru.alinadorozhkina.deezer_music.databinding.FragmentChartBinding
import ru.alinadorozhkina.deezer_music.databinding.ItemTopArtistBinding
import ru.alinadorozhkina.deezer_music.databinding.ItemTopBinding
import ru.alinadorozhkina.deezer_music.mvp.ui.image.GlideImageLoader
import ru.alinadorozhkina.deezer_music.mvp.model.image.IImageLoader
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Album
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Artist
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Chart
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.presenter.ChartPresenter
import ru.alinadorozhkina.deezer_music.mvp.presenter.list.TopAlbumsListPresenter
import ru.alinadorozhkina.deezer_music.mvp.presenter.list.TopArtistListPresenter
import ru.alinadorozhkina.deezer_music.mvp.presenter.list.TopTracksListPresenter
import ru.alinadorozhkina.deezer_music.mvp.ui.adapter.BaseAdapter
import ru.alinadorozhkina.deezer_music.mvp.ui.base.BaseFragment
import ru.alinadorozhkina.deezer_music.mvp.ui.navigation.AndroidScreens

class ChartFragment :
    BaseFragment<FragmentChartBinding, Chart, ChartPresenter>(),
    ChartContract.View {

    override var bindingNullable: FragmentChartBinding? = null
    private val imageLoader: IImageLoader<ImageView> = GlideImageLoader()

    @InjectPresenter
    override lateinit var presenter: ChartPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentChartBinding
        .inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun renderSuccess(data: Chart) = with(binding) {
        rvTopTracks.adapter = BaseAdapter(
            TopTracksListPresenter(App.INSTANCE.router, AndroidScreens(), data.tracks.data),
            R.layout.item_top
        )
        { view, data ->
            chartTopTracksBind(view, data, imageLoader)
        }

        rvTopAlbums.adapter = BaseAdapter(
            TopAlbumsListPresenter(data.albums.data),
            R.layout.item_top
        )
        { view, data ->
            chartTopAlbumsBind(view, data, imageLoader)
        }

        rvTopArtist.adapter = BaseAdapter(
            TopArtistListPresenter(data.artists.data),
            R.layout.item_top_artist
        )
        { view, data ->
            chartTopArtistBind(view, data, imageLoader)
        }
    }


    private fun chartTopTracksBind(view: View, data: Track, imageLoader: IImageLoader<ImageView>) {
        val rvBinding = ItemTopBinding.bind(view)
        with(rvBinding) {
            tvPosition.text = data.position.toString()
            tvTitle.text = data.title
            tvSubtitle.text = data.artist.name
            imageLoader.load(data.artist.picture, ivPoster)
        }
    }

    private fun chartTopAlbumsBind(view: View, data: Album, imageLoader: IImageLoader<ImageView>) {
        val rvBinding = ItemTopBinding.bind(view)
        with(rvBinding) {
            tvPosition.text = data.position.toString()
            tvTitle.text = data.title
            tvSubtitle.text = data.artist.name
            imageLoader.load(data.cover, ivPoster)
        }
    }

    private fun chartTopArtistBind(view: View, data: Artist, imageLoader: IImageLoader<ImageView>) {
        val rvBinding = ItemTopArtistBinding.bind(view)
        with(rvBinding) {
            tvPosition.text = data.position.toString()
            tvName.text = data.name
            imageLoader.load(data.picture, ivPoster)
        }
    }

    companion object {
        fun newInstance() = ChartFragment()
    }
}