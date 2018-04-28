package com.github.warrocker.githubrepos.core.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.github.warrocker.githubrepos.core.database.dao.RepoDao
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem

/**
 * Created by Warrocker on 10.12.2017.
 */
@Database(entities = [(RepoItem::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun repoDao(): RepoDao
}