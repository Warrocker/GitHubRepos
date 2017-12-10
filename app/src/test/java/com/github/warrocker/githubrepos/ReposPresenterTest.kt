package com.github.warrocker.githubrepos

import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.ui.reposScreen.IReposModel
import com.github.warrocker.githubrepos.ui.reposScreen.IReposView
import com.github.warrocker.githubrepos.ui.reposScreen.ReposPresenterImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Warrocker on 10.12.2017.
 */
/** @see javaReposPresenterTest
 *  Kotlin don`t work with this test, but java do
 */
@RunWith(MockitoJUnitRunner::class)
class ReposPresenterTest {
    @Mock
    var view: IReposView? = null
    @Mock
    var model: IReposModel? = null
    private var presenter: ReposPresenterImpl? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = model?.let { view?.let { it1 -> ReposPresenterImpl(it, it1) } }
    }

    @Test
    fun progressBarShowTest() {
        presenter?.onSearchCalled("test")
        verify(view, times(1))?.startProgressBar()
    }

    @Test
    fun progressBarStopTestOnFinished() {
        presenter?.onLoadFinished(ArrayList())
        verify(view, times(1))?.stopProgressBar()
    }

    @Test
    fun progressBarStopTestOnCancelled() {
        presenter?.onSearchCancelled()
        verify(view, times(1))?.stopProgressBar()
    }

    @Test
    fun progressBarStopTestOnInterrupted() {
        presenter?.onLoadInterrupted()
        verify(view, times(1))?.stopProgressBar()
    }
    @Test
    fun checkDataUpdating(){
        val items = ArrayList<RepoItem>()
        presenter?.onLoadFinished(items)
        verify(view, times(1))?.updateViewWithData(items)
        verify(view, times(1))?.stopProgressBar()
    }
}