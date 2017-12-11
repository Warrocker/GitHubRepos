package com.github.warrocker.githubrepos.core.database.dao

import android.arch.persistence.room.*
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import io.reactivex.Single


/**
 * Created by Warrocker on 10.12.2017.
 */
@Dao
interface RepoDao {
    @Query("SELECT * FROM repoItem")
    fun getAllByName(): Single<List<RepoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repos: List<RepoItem>)


    @Delete
    fun delete(repo: RepoItem)

    @Query("DELETE FROM repoItem")
    fun clearTable()
}