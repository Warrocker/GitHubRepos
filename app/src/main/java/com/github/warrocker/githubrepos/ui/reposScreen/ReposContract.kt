package com.github.warrocker.githubrepos.ui.reposScreen

import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.mvp.BasePresenter

/**
 * Created by warrocker on 17.02.18.
 */
interface ReposContract {
    interface View {
        fun updateViewWithData(repositories: List<RepoItem>)
        fun startProgressBar()
        fun stopProgressBar()
        fun setSearchView()
        fun showEmptyView()
        fun hideEmptyView()
        fun clearItems()
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun onSearchCalled(searchString: String)
        abstract fun onSearchCancelled()
    }
}