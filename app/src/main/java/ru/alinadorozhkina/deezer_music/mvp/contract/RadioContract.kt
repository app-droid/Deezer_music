package ru.alinadorozhkina.deezer_music.mvp.contract

import io.reactivex.Single
import ru.alinadorozhkina.deezer_music.mvp.model.entities.*

interface RadioContract {

    interface View : IBaseView<Category>

    interface Presenter : IPresenter<Category, View> {
        fun getCategoriesData()
    }

    interface Repository {
        fun getData(): Single<Category>
    }

    interface DataSource  {
        fun getData(): Single<List<CategoryModel>>
    }

    interface Interactor{
        fun getData(): Single<AppState<Category>>
    }
}