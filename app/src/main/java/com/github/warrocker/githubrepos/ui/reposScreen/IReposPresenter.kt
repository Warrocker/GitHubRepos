package com.github.warrocker.githubrepos.ui.reposScreen

/**
 * Created by Warrocker on 10.12.2017.
 */
interface IReposPresenter {
    fun onSearchCalled(searchString : String)
    fun onSearchCancelled()
}