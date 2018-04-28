package com.github.warrocker.githubrepos.ui.reposScreen

import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Warrocker on 10.12.2017.
 */
class ReposPresenter(private val reposRepository: ReposRepository)
    : ReposContract.Presenter() {
    private var observer: DisposableSingleObserver<List<RepoItem>>? = null

    override fun onSearchCalled(searchString: String) {
        observer = object : DisposableSingleObserver<List<RepoItem>>() {
            override fun onSuccess(t: List<RepoItem>) {
                showResult(t)
            }

            override fun onError(e: Throwable) {
                showEmptyResult()
            }
        }
        reposRepository.loadRepos(searchString)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnSubscribe {
                    clearOldDataAndShowProgress()
                }?.subscribeWith(observer)
    }

    private fun clearOldDataAndShowProgress() {
        view?.let {
            it.startProgressBar()
            it.clearItems()
            it.hideEmptyView()
        }
    }

    private fun showResult(response: List<RepoItem>) {
        view?.let {
            it.stopProgressBar()
            it.updateViewWithData(response)
            it.setSearchView()
        }
    }

    override fun onSearchCancelled() {
        observer?.dispose()
        showEmptyResult()
    }

    private fun showEmptyResult() {
        view?.let {
            it.stopProgressBar()
            it.setSearchView()
            it.showEmptyView()
        }
    }

    override fun onDestroyed() {
        super.onDestroyed()
        observer?.dispose()
    }
}