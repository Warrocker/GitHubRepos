package com.github.warrocker.githubrepos

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.github.warrocker.githubrepos.core.ActivityContextKeeper
import com.github.warrocker.githubrepos.interfaces.IActivityContextSender
import com.github.warrocker.githubrepos.ui.GitHubActivity
import com.github.warrocker.githubrepos.ui.reposScreen.ReposFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Warrocker on 10.12.2017.
 */
class MainScreenTest {
    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<GitHubActivity> = ActivityTestRule<GitHubActivity>(GitHubActivity::class.java)

    @Before
    fun setUpFragment(){
        mActivityRule.activity.fragmentManager
                .beginTransaction()
                .replace((ActivityContextKeeper.instance?.context as IActivityContextSender).getContainer(), ReposFragment())
                .addToBackStack(null)
                .commit()
    }
    @Test
    fun mainScreen(){
        Espresso.onView(ViewMatchers.withId(R.id.textInputLayoutSearch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.etSearch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.etSearch)).perform(typeText("retrofit"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withText("retrofit")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.ivSearch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}