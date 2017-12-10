package com.github.warrocker.githubrepos

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.github.warrocker.githubrepos.ui.GitHubActivity
import org.junit.Rule
import org.junit.Test

/**
 * Created by Warrocker on 10.12.2017.
 */
class SplashScreenTest {
    @Rule@JvmField
    var mActivityRule: ActivityTestRule<GitHubActivity> = ActivityTestRule<GitHubActivity>(GitHubActivity::class.java)

    @Test
    fun splash(){
        onView(withId(R.id.ivLogo)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
    }

}