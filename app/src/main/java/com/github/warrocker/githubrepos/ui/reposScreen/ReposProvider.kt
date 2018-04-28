package com.github.warrocker.githubrepos.ui.reposScreen

import com.github.warrocker.githubrepos.core.database.dao.DatabaseModule
import com.github.warrocker.githubrepos.core.rest.NetworkModule

class ReposProvider {
    private var presenter: ReposContract.Presenter? = null

    private object Holder {
        val INSTANCE = ReposProvider()
    }

    companion object {
        val instance: ReposProvider by lazy { Holder.INSTANCE }
    }

    fun providePresenter(): ReposContract.Presenter{
        if(presenter == null){
            presenter = ReposPresenter(ReposRepository(NetworkModule.instance.serverApi, DatabaseModule.instance))
        }
            return presenter!!
    }
}
