package com.github.warrocker.githubrepos.core

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.support.annotation.DrawableRes
import android.view.View
import android.widget.ImageView

/**
 * Created by Warrocker on 09.12.2017.
 */
object AnimatorHelper {
    private val SHOW_DURATION: Long = 200
    private val TO_NORMAL_DURATION: Long = 100
    private val HIDE_DURATION: Long = 200
    private val HIDE_DELAY: Long = 400
    private var isAnimate = false

    fun likeAnimation(@DrawableRes icon: Int, view: ImageView?) {
        if (view != null && !isAnimate) {
            val set = AnimatorSet()
            set.playSequentially(
                    showAnimatorSet(view),
                    toNormalAnimatorSet(view),
                    hideAnimatorSet(view))
            set.addListener(getLikeEndListener(view, icon))
            set.start()
            view.animate().alphaBy(0f).alpha(1f).start()
        }
    }

    private fun getLikeEndListener(view: ImageView, icon: Int): AnimatorListenerAdapter {
        return object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                isAnimate = true
                view.visibility = View.VISIBLE
                view.setImageResource(icon)
                view.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                isAnimate = false
                view.visibility = View.GONE
                view.setImageDrawable(null)
                view.setLayerType(View.LAYER_TYPE_NONE, null)
            }
        }
    }

    private fun showAnimatorSet(view: View): AnimatorSet {
        val set = AnimatorSet()
        set.setDuration(SHOW_DURATION).playTogether(
                ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat(view, View.SCALE_X, 0.2f, 1.4f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.2f, 1.4f)
        )
        return set
    }

    private fun toNormalAnimatorSet(view: View): AnimatorSet {
        val set = AnimatorSet()
        set.setDuration(TO_NORMAL_DURATION).playTogether(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 1.4f, 1f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.4f, 1f)
        )
        return set
    }

    private fun hideAnimatorSet(view: View): AnimatorSet {
        val set = AnimatorSet()
        set.setDuration(HIDE_DURATION).playTogether(
                ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f),
                ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.2f),
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.2f)
        )
        set.startDelay = HIDE_DELAY
        return set
    }
}