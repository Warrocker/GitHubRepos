package com.github.warrocker.githubrepos.interfaces

import android.content.Context

/**
 * Created by Warrocker on 09.12.2017.
 */
interface IActivityContextSender {
    fun getMainContext(): Context

    fun getContainer(): Int
}