package com.github.warrocker.githubrepos.interfaces

/**
 * Created by Warrocker on 10.12.2017.
 */
interface OnFinishLoadListener<in T> {
    fun onLoadFinished(response : T )
    fun onLoadInterrupted()
}