package com.github.warrocker.githubrepos.ui.reposScreen

import com.github.warrocker.githubrepos.core.database.dao.DatabaseModule
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.core.rest.ApiService
import io.reactivex.Single

/**
 * Created by Warrocker on 10.12.2017.
 */
class ReposRepository(private val apiService: ApiService, private val databaseModule: DatabaseModule) {
    fun loadRepos(searchString: String): Single<List<RepoItem>>? {
        return if (databaseModule.lastSearch == searchString) {
            databaseModule.getReposFromDB()
        } else {
            apiService.getUserProjects(searchString)
                    .map { it.items!! }
                    .doOnSuccess {
                        databaseModule.lastSearch = searchString
                        databaseModule.saveRepoToDB(it)
                    }
        }
    }
}