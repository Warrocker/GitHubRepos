package com.github.warrocker.githubrepos.core.rest

import com.github.warrocker.githubrepos.core.entity.reposentities.Repositories
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Warrocker on 10.12.2017.
 */
interface ApiService {
    @GET("/search/repositories")
    fun getUserProjects(@Query("q") queryString: String): Single<Repositories>
}