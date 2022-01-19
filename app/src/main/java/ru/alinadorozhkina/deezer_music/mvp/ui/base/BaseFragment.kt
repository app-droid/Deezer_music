package ru.alinadorozhkina.deezer_music.mvp.ui.base

import android.widget.Toast
import androidx.viewbinding.ViewBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.ProvidePresenter
import ru.alinadorozhkina.deezer_music.mvp.contract.AppState
import ru.alinadorozhkina.deezer_music.mvp.contract.AppStateEntity
import ru.alinadorozhkina.deezer_music.mvp.presenter.base.BasePresenter
import ru.alinadorozhkina.deezer_music.mvp.contract.IBaseView

abstract class BaseFragment<
        VB : ViewBinding,
        E : AppStateEntity,
        P : BasePresenter<*, *>>
    : MvpAppCompatFragment(), IBaseView<E> {

    protected abstract var bindingNullable: VB?
    protected val binding get() = bindingNullable!!
    abstract var presenter: P


    @ProvidePresenter
    open fun providePresenter() = presenter


    override fun renderData(baseState: AppState<E>) {
        when (baseState) {
            is AppState.Success -> renderSuccess(baseState.data)
            is AppState.Error -> renderError(baseState.error)
        }
    }

    abstract fun renderSuccess(data: E)

    private fun renderError(error: Throwable) {
        error.message?.let { showMessage(it) }
    }

    protected fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
    }
}

