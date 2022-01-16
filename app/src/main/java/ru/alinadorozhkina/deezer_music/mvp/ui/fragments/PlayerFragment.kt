package ru.alinadorozhkina.deezer_music.mvp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.alinadorozhkina.deezer_music.R
import ru.alinadorozhkina.deezer_music.databinding.FragmentPlayerBinding
import ru.alinadorozhkina.deezer_music.databinding.FragmentRadioBinding
import ru.alinadorozhkina.deezer_music.databinding.TracklistItemBinding
import ru.alinadorozhkina.deezer_music.mvp.contract.PlayerContact
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.model.entities.TrackList
import ru.alinadorozhkina.deezer_music.mvp.model.image.IImageLoader
import ru.alinadorozhkina.deezer_music.mvp.presenter.PlayerPresenter
import ru.alinadorozhkina.deezer_music.mvp.presenter.RadioPresenter
import ru.alinadorozhkina.deezer_music.mvp.presenter.list.PlayerListPresenter
import ru.alinadorozhkina.deezer_music.mvp.ui.adapter.BaseAdapter
import ru.alinadorozhkina.deezer_music.mvp.ui.base.BaseFragment
import ru.alinadorozhkina.deezer_music.mvp.ui.image.GlideImageLoader


class PlayerFragment : BaseFragment<FragmentPlayerBinding,
        TrackList,
        PlayerPresenter>(), PlayerContact.View {

    override var bindingNullable: FragmentPlayerBinding? = null
    private val imageLoader: IImageLoader<ImageView> = GlideImageLoader()

    private val track: Track?
        get() = arguments?.getParcelable("track")

    private var flag = true

    @InjectPresenter
    override lateinit var presenter: PlayerPresenter

    @ProvidePresenter
    override fun providePresenter() = PlayerPresenter(track!!)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlayerBinding.inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playButton.setOnClickListener {
            flag = if (flag) {
                binding.playButton.setImageResource(R.drawable.ic_baseline_pause_24)
                false
            } else {
                binding.playButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                true
            }
            presenter.playClicked()
        }
    }

    override fun renderSuccess(data: TrackList) = with(binding)  {
        Log.d("PlayerFragment", data.toString())
        rvTracklist.adapter = BaseAdapter(PlayerListPresenter(data.data),
        R.layout.tracklist_item)
        { view, data ->
            playerBind(view, data, imageLoader)
        }
    }

    private fun playerBind(view: View, data: Track, imageLoader: IImageLoader<ImageView>){
        val rvBinding = TracklistItemBinding.bind(view)
        with (rvBinding) {
            imageLoader.load(data.album.cover, ivTrackPoster)
            tvTrackTitle.text = data.title
            tvTrackTitle.isSelected = true
        }
    }

    override fun setBackground(url: String) {
        imageLoader.load(url, binding.ivBackground)
    }

    override fun setArtistName(name: String) {
        binding.tvArtistName.text = name
    }

    override fun setTrackTitle(title: String) {
        binding.tvTrackTitle.text = title
    }

    override fun seekbarMax(duration: Int) {
        binding.seekbar.max = duration
    }

    override fun seekbarProgress(progress: Int) {
        binding.seekbar.progress = progress
    }

    companion object {
        fun newInstance(track: Track): PlayerFragment {
            val args = Bundle()
            args.putParcelable("track", track)
            val fragment = PlayerFragment()
            fragment.arguments = args
            return fragment
        }
    }

}