package ru.alinadorozhkina.deezer_music.mvp.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.alinadorozhkina.deezer_music.mvp.model.image.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun load(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}