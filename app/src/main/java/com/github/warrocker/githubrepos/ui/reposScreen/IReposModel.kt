package com.github.warrocker.githubrepos.ui.reposScreen

import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.interfaces.OnFinishLoadListener

/**
 * Created by Warrocker on 10.12.2017.
 */
interface IReposModel {
    fun loadRepos(searchString : String, responseListener : OnFinishLoadListener<List<RepoItem>>)
    fun cancelLoad()
}