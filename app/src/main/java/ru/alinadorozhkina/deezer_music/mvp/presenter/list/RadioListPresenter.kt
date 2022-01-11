package ru.alinadorozhkina.deezer_music.mvp.presenter.list

import ru.alinadorozhkina.deezer_music.mvp.model.entities.CategoryModel
import ru.alinadorozhkina.deezer_music.mvp.model.entities.Track
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BaseListPresenter

class RadioListPresenter(
    data: List<CategoryModel>
) : BaseListPresenter<CategoryModel>(data)

class ChildRadioListPresenter(
    data: List<Track>
) :BaseListPresenter<Track>(data)

//class ChildRadioListPresenter(
//    url: String,
//    val interactor: RadioContract.Interactor<Tracklist> = TrackListInteractor(
//        TrackListRepoImpl(DataSourceTrackList()))
//) : IListPresenter<Track, IDataItemView<Track>> {
//    override var itemClickListener: ((IDataItemView<Track>) -> Unit)? = null
//
//    val trackList = mutableMapOf<String,Track>()
//    private val compositeDisposable: CompositeDisposable
//    private val schedulerProvider: SchedulerProvider
//
//    init {
//        schedulerProvider = SchedulerProvider()
//        compositeDisposable = CompositeDisposable()
//        getTrackList(url)
//    }
//
//    private fun getTrackList(url: String) {
//        Log.d("ChildRadioListPresenter url", url)
//        //val tracks = mutableListOf<Track>()
//        compositeDisposable.add(
//            interactor.getData(url)
//                .subscribeOn(schedulerProvider.io)
//                .observeOn(schedulerProvider.ui)
//                .subscribeWith(getObserver())
//        )
//    }
//
//    private fun getObserver (): DisposableObserver<Tracklist> {
//        return object : DisposableObserver<Tracklist>(){
//
//            override fun onError(e: Throwable) {
//                Log.d("ChildRadioListPresenter Error", e.message.toString())
//            }
//
//
//            override fun onNext(t: Tracklist) {
//                Log.d("ChildRadioListPresenter onSuccess", t.toString())
//                if (trackList.isNotEmpty()){
//                    trackList.clear()
//                }
//               // trackList.put()
//            }
//
//            override fun onComplete() {
//                TODO("Not yet implemented")
//            }
//        }
//    }
//
//    override fun bindView(view: IDataItemView<Track>) {
//        val unity =trackList[view.pos]
//        view.bind(unity)
//    }
//
//
//    override fun getCount(): Int = trackList.size
//}



//class RadioListPresenter(
//    data: List<Genre>,
//    val interactor: RadioContract.Interactor<Tracklist> = TrackListInteractor(
//        TrackListRepoImpl(DataSourceTrackList())
//    )
//) : BaseListPresenter<Genre>(data) {
//
//    override fun bindView(view: IDataItemView<Genre>) {
//        val unity = data[view.pos]
//        view.bind(unity)
//        getTrackList(unity.tracklist)
//        super.bindView(view)
//    }
//
//    val tracks = mutableListOf<Track>()
//    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
//    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
//
//    fun getTrackList(url: String){
//        compositeDisposable.add(
//                interactor.getData(url)
//                    .subscribeOn(schedulerProvider.io)
//                    .observeOn(schedulerProvider.ui)
//                    .subscribe({
//                               tracks.addAll(it.data)
//                    },{
//
//                    })
//            )
//    }
//
//    inner class ChildRadioListPresenter: BaseListPresenter<Track>(tracks)


//    inner class ChildRadioListPresenter(
//        val url: String,
//        val interactor: RadioContract.Interactor<Tracklist> = TrackListInteractor(
//            TrackListRepoImpl(DataSourceTrackList()))
//    ) : IListPresenter<Track, IDataItemView<Track>> {
//
//        val tracks = mutableListOf<Track>()
//
//        private val compositeDisposable: CompositeDisposable = CompositeDisposable()
//        private val schedulerProvider: SchedulerProvider = SchedulerProvider()
//
//        override var itemClickListener: ((IDataItemView<Track>) -> Unit)? = null
//
//         fun getTrackListData() {
//            compositeDisposable.add(
//                interactor.getData(url)
//                    .subscribeOn(schedulerProvider.io)
//                    .observeOn(schedulerProvider.ui)
//                    .subscribe({
//                               tracks.addAll(it.data)
//                    },{
//
//                    })
//            )
//
//        }
//
//        override fun bindView(view: IDataItemView<Track>) {
//            val unity = tracks[view.pos]
//            view.bind(unity)
//        }
//
//        override fun getCount(): Int {
//            TODO("Not yet implemented")
//        }
//    }
