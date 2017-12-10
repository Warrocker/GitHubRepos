package com.github.warrocker.githubrepos.core

import android.app.Application
import android.arch.persistence.room.Room
import com.github.warrocker.githubrepos.core.database.AppDatabase


/**
 * Created by Warrocker on 10.12.2017.
 */
class GitHubApplication : Application() {
    var db : AppDatabase? = null
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "github_database").build()
        instance = this

    }
    companion object {
        lateinit var instance : GitHubApplication
    }
}