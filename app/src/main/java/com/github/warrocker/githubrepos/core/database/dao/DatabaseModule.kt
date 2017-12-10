package com.github.warrocker.githubrepos.core.database.dao

import com.github.warrocker.githubrepos.core.GitHubApplication
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.interfaces.OnFinishLoadListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Warrocker on 10.12.2017.
 */
object DatabaseModule {
    var lastSearch : String? = ""
    fun saveRepoToDB(repos : List<RepoItem>){

        object : Thread() {
            override fun run() {
                GitHubApplication.instance.db?.repoDao()?.insertAll(repos)
            }
        }.start()
    }
    fun getReposFromDB(responseListener: OnFinishLoadListener<List<RepoItem>>){
        GitHubApplication.instance.db?.repoDao()?.getAll()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<List<RepoItem>>(){
                    override fun onSuccess(t: List<RepoItem>?) {
                        t?.let { responseListener.onLoadFinished(it) }
                    }

                    override fun onError(e: Throwable?) {
                        responseListener.onLoadInterrupted()
                    }
                })
    }
}