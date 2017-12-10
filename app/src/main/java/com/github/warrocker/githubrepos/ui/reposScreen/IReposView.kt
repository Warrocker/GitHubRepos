package com.github.warrocker.githubrepos.ui.reposScreen

import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem

/**
 * Created by Warrocker on 10.12.2017.
 */
interface IReposView {
    fun updateViewWithData(repositories: List<RepoItem>)
    fun startProgressBar()
    fun stopProgressBar()
    fun setCancelView()
    fun setSearchView()
    fun setEmptyView(visible : Boolean)
    fun clearItems()
}