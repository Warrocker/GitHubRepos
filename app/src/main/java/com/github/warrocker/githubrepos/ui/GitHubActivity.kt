package com.github.warrocker.githubrepos.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.warrocker.githubrepos.R
import com.github.warrocker.githubrepos.core.ActivityContextKeeper
import com.github.warrocker.githubrepos.interfaces.IActivityContextSender
import com.github.warrocker.githubrepos.ui.splashScreen.SplashFragment
import com.github.warrocker.githubrepos.utils.FragmentUtils

/**
 * Created by Warrocker on 09.12.2017.
 */

class GitHubActivity : AppCompatActivity(), IActivityContextSender {
    override val mainContext: Context? = this
    override val container: Int? = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)
        ActivityContextKeeper.instance?.context = this
        FragmentUtils.replaceFragment(SplashFragment(), false)
    }
}