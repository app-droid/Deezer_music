package ru.alinadorozhkina.deezer_music.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import moxy.presenter.InjectPresenter
import ru.alinadorozhkina.deezer_music.R
import ru.alinadorozhkina.deezer_music.databinding.ChildGenreItemBinding
import ru.alinadorozhkina.deezer_music.databinding.FragmentRadioBinding
import ru.alinadorozhkina.deezer_music.databinding.ParentItemGenreBinding
import ru.alinadorozhkina.deezer_music.mvp.contract.RadioContract
import ru.alinadorozhkina.deezer_music.mvp.model.entities.*
import ru.alinadorozhkina.deezer_music.mvp.model.image.IImageLoader
import ru.alinadorozhkina.deezer_music.mvp.presenter.RadioPresenter
import ru.alinadorozhkina.deezer_music.mvp.presenter.list.ChildRadioListPresenter
import ru.alinadorozhkina.deezer_music.mvp.presenter.list.RadioListPresenter
import ru.alinadorozhkina.deezer_music.mvp.ui.adapter.BaseAdapter
import ru.alinadorozhkina.deezer_music.mvp.ui.base.BaseFragment
import ru.alinadorozhkina.deezer_music.mvp.ui.image.GlideImageLoader

class RadioFragment :
    BaseFragment<FragmentRadioBinding, Category, RadioPresenter>(),
    RadioContract.View {

    override var bindingNullable: FragmentRadioBinding? = null
    private val imageLoader: IImageLoader<ImageView> = GlideImageLoader()

    @InjectPresenter
    override lateinit var presenter: RadioPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRadioBinding.inflate(inflater, container, false)
        .apply { bindingNullable = this }
        .root

    override fun renderSuccess(data: Category)  = with (binding) {
                rvParent.adapter = BaseAdapter(
                        RadioListPresenter(data.data),
            R.layout.parent_item_genre
        )
        { view, data ->
            radioBind(view, data)
        }
    }

    private fun radioBind(view: View, category: CategoryModel) {
        val rvBinding = ParentItemGenreBinding.bind(view)
        with(rvBinding) {
            tvTitle.text = category.title
            rvChild.adapter = BaseAdapter(
                ChildRadioListPresenter(category.tracks),
                R.layout.child_genre_item
            )
            { view, data ->
                childBind(view, data, imageLoader)
            }
        }
    }

    private fun childBind(view: View, data: Track, imageLoader: IImageLoader<ImageView>) {
        val rvBinding = ChildGenreItemBinding.bind(view)
        with(rvBinding) {
            tvTitle.text = data.artist.name
            tvSubtitle.text = data.title
            imageLoader.load(data.artist.picture, ivPoster)
        }
    }

    companion object {
        fun newInstance() = RadioFragment()
    }
}