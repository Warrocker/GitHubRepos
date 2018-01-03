package com.github.warrocker.githubrepos.ui.reposScreen

import com.github.warrocker.githubrepos.core.database.dao.DatabaseModule
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.core.entity.reposentities.Repositories
import com.github.warrocker.githubrepos.core.rest.NetworkModule
import com.github.warrocker.githubrepos.interfaces.OnFinishLoadListener
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by Warrocker on 10.12.2017.
 */
class ReposModelImpl : IReposModel {
    private var networkModule = NetworkModule()
    private var observer: DisposableSingleObserver<Repositories>? = null
    private var dbobserver: DisposableSingleObserver<List<RepoItem>>? = null
    //    private var dbObserver : Subscriber<List<RepoItem>>? = null
    override fun loadRepos(searchString: String, responseListener: OnFinishLoadListener<List<RepoItem>>) {
        if (DatabaseModule.lastSearch == searchString) {
            dbobserver = DatabaseModule.getReposFromDB(responseListener)
        } else {
            observer = networkModule.getRepoFromNetwork(searchString, responseListener)
        }
    }

    override fun cancelLoad() {
        if (observer?.isDisposed == false) {
            observer?.dispose()
        } else if (dbobserver?.isDisposed == false) {
            dbobserver!!.dispose()
        }
    }
}