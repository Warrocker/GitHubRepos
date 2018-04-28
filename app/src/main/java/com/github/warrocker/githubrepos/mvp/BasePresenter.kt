package com.github.warrocker.githubrepos.mvp

/**
 * Created by warrocker on 17.02.18.
 */
abstract class BasePresenter<T> {
    var view: T? = null
    open fun onDestroyed() {
        view = null
    }
}