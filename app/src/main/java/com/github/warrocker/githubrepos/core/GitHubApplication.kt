package com.github.warrocker.githubrepos.core

import android.app.Application
import com.github.warrocker.githubrepos.core.database.dao.DatabaseModule
import com.github.warrocker.githubrepos.core.rest.NetworkModule


/**
 * Created by Warrocker on 10.12.2017.
 */
class GitHubApplication : Application() {
    companion object {
        lateinit var sharedInstance : GitHubApplication
    }
    override fun onCreate() {
        super.onCreate()
        sharedInstance = this
        DatabaseModule.instance.setUpDatabase(this)
        NetworkModule.instance.setUpNetworkModule()
    }
}