package com.github.warrocker.githubrepos.core

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Warrocker on 10.12.2017.
 */
object GlobalUtils {
    fun switchOfTheKeyBoard(view: View?) {
        // switch off keyboard
        if (view != null) {
            val imm = GitHubApplication.instance.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}