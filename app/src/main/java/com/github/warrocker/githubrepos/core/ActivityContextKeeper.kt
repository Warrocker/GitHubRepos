package com.github.warrocker.githubrepos.core

import com.github.warrocker.githubrepos.interfaces.IActivityContextSender

/**
 * Created by Warrocker on 09.12.2017.
 */
class ActivityContextKeeper private constructor() {
    var context: IActivityContextSender? = null

    companion object {
        var instance: ActivityContextKeeper? = null
            get() {
                if (field == null)
                    this.instance = ActivityContextKeeper()
                return field
            }
    }
}