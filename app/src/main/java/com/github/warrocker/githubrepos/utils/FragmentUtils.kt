package com.github.warrocker.githubrepos.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.github.warrocker.githubrepos.core.ActivityContextKeeper

/**
 * Created by Warrocker on 09.12.2017.
 */
object FragmentUtils {

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        replaceFragment(fragment, null, addToBackStack)
    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        addFragment(fragment, null, addToBackStack)
    }

    fun addFragment(fragment: Fragment, args: Bundle?, addToBackStack: Boolean) {
        changeFragmentInternal(fragment, args, addToBackStack, false)
    }

    fun replaceFragment(fragment: Fragment, args: Bundle?, addToBackStack: Boolean) {
        changeFragmentInternal(fragment, args, addToBackStack, true)
    }

    private fun changeFragmentInternal(fragment: Fragment, args: Bundle?, addToBackStack: Boolean, asReplace: Boolean) {
        val sender = ActivityContextKeeper.instance?.context
        val transaction = (sender as FragmentActivity).supportFragmentManager.beginTransaction()
//        transaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_in, R.animator.fade_out)
        args?.let { fragment.arguments = it }
        val name = fragment.javaClass.name
        if (asReplace) {
            transaction.replace(sender.container!!, fragment, name)
        } else {
            transaction.add(sender.container!!, fragment, name)
        }
        if (addToBackStack) transaction.addToBackStack(name)
        transaction.commit()
    }
}