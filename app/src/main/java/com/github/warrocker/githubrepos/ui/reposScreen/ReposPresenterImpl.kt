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
        val view = reposViewReference?.get()
        view?.stopProgressBar()
        view?.setSearchView()
        view?.setEmptyView(true)
    }

    override fun onLoadFinished(response: List<RepoItem>) {
        val view = reposViewReference?.get()
        view?.stopProgressBar()
        view?.updateViewWithData(response)
        view?.setSearchView()
    }



    override fun onSearchCalled(searchString: String) {
        val view = reposViewReference?.get()
        reposModel.loadRepos(searchString, this)
        view?.startProgressBar()
        view?.setCancelView()
        view?.clearItems()
        view?.setEmptyView(false)
    }

    override fun onSearchCancelled() {
        val view = reposViewReference?.get()
        reposModel.cancelLoad()
        view?.stopProgressBar()
        view?.setSearchView()
        view?.setEmptyView(true)

    }

}