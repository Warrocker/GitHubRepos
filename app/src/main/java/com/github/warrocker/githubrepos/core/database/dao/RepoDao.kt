package com.github.warrocker.githubrepos.core.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import io.reactivex.Single


/**
 * Created by Warrocker on 10.12.2017.
 */
@Dao
interface RepoDao {
    @Query("SELECT * FROM repoItem")
    fun getAll(): Single<List<RepoItem>>

    @Insert
    fun insertAll(repos: List<RepoItem>)

    @Delete
    fun delete(repo: RepoItem)

}