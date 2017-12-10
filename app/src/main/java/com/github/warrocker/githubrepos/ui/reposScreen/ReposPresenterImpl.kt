package com.github.warrocker.githubrepos.ui.reposScreen

import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.interfaces.OnFinishLoadListener
import java.lang.ref.WeakReference

/**
 * Created by Warrocker on 10.12.2017.
 */
class ReposPresenterImpl(private var reposModel: IReposModel, reposView : IReposView) : IReposPresenter, OnFinishLoadListener<List<RepoItem>> {
    private var reposViewReference : WeakReference<IReposView>? = null
    init {
        reposViewReference = WeakReference(reposView)
    }
    override fun onLoadInterrupted() {
        reposViewReference?.get()?.stopProgressBar()
        reposViewReference?.get()?.setSearchView()
        reposViewReference?.get()?.setEmptyView(true)
    }

    override fun onLoadFinished(response: List<RepoItem>) {
        reposViewReference?.get()?.stopProgressBar()
        reposViewReference?.get()?.updateViewWithData(response)
        reposViewReference?.get()?.setSearchView()
    }



    override fun onSearchCalled(searchString: String) {
        reposViewReference?.get()?.startProgressBar()
        reposModel.loadRepos(searchString, this)
        reposViewReference?.get()?.setCancelView()
        reposViewReference?.get()?.clearItems()
        reposViewReference?.get()?.setEmptyView(false)
    }

    override fun onSearchCancelled() {
        reposViewReference?.get()?.stopProgressBar()
        reposViewReference?.get()?.setSearchView()
        reposModel.cancelLoad()
        reposViewReference?.get()?.setEmptyView(true)

    }

}