package com.github.warrocker.githubrepos.core.database.dao

import android.arch.persistence.room.Room
import android.content.Context
import com.github.warrocker.githubrepos.core.database.AppDatabase
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import io.reactivex.Single

/**
 * Created by Warrocker on 10.12.2017.
 */
class DatabaseModule private constructor(){
    var lastSearch: String? = ""
    private lateinit var db : AppDatabase

    private object Holder { val INSTANCE = DatabaseModule() }
    companion object {
        private const val DATABASE_NAME = "github_database"
        val instance: DatabaseModule by lazy { Holder.INSTANCE }
    }
    fun setUpDatabase(applicationContext : Context) {
        db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, DATABASE_NAME).build()
    }
    fun saveRepoToDB(repos: List<RepoItem>) {
        object : Thread() {
            override fun run() {
                db.repoDao().clearTable()
                db.repoDao().insertAll(repos)
            }
        }.start()
    }

    fun getReposFromDB(): Single<List<RepoItem>>? {
        return db.repoDao().getAllByName()
    }
}