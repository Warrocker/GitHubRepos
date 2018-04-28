package com.github.warrocker.githubrepos.ui.splashScreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.github.warrocker.githubrepos.R
import com.github.warrocker.githubrepos.core.ActivityContextKeeper
import com.github.warrocker.githubrepos.core.AnimatorHelper
import com.github.warrocker.githubrepos.core.DetailsTransition
import com.github.warrocker.githubrepos.ui.base.BaseFragment
import com.github.warrocker.githubrepos.ui.reposScreen.ReposFragment
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import net.frakbot.jumpingbeans.JumpingBeans

/**
 * Created by Warrocker on 09.12.2017.
 */
class SplashFragment : BaseFragment() {
    companion object {
        private const val BEANS_DURATION = 1200
        private const val BEANS_DELAY = 3000
        private const val ANIM_DELAY = 1500
    }

    private var jumpingBuilder: JumpingBeans? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        retainInstance = true
        jumpingBuilder = JumpingBeans
                .with(tvTitle)
                .appendJumpingDots()
                .setIsWave(true)
                .setLoopDuration(BEANS_DURATION).build()

        Handler().postDelayed({ this.startReposFragment() }, BEANS_DELAY.toLong())
        Handler().postDelayed({ AnimatorHelper.likeAnimation(R.drawable.github_logo_for_anim, ivLogo) }, ANIM_DELAY.toLong())
    }

    private fun startReposFragment() {
        jumpingBuilder?.stopJumping()
        if (activity == null) return

        val reposListFragment = ReposFragment.newInstance()
        val animation = AnimationUtils.loadAnimation(activity, R.anim.fadeout)
        tvTitle.startAnimation(animation)

        val transition = DetailsTransition()
        reposListFragment.sharedElementEnterTransition = transition
        reposListFragment.sharedElementReturnTransition = transition

        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.addSharedElement(ivLogo, getString(R.string.logo))
                ?.replace(ActivityContextKeeper.instance?.context?.container!!, reposListFragment)?.addToBackStack(null)?.commit()
    }
}