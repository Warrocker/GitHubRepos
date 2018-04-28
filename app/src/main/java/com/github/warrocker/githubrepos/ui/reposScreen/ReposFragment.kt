package com.github.warrocker.githubrepos.ui.reposScreen

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.warrocker.githubrepos.R
import com.github.warrocker.githubrepos.core.GlobalUtils
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.ui.base.BaseFragment
import com.github.warrocker.githubrepos.ui.reposScreen.adapters.RvRepoAdapter
import kotlinx.android.synthetic.main.fragment_user_repo.*

/**
 * Created by Warrocker on 10.12.2017.
 */
class ReposFragment : BaseFragment(), ReposContract.View {
    companion object {
        fun newInstance(): ReposFragment {
            val fragment = ReposFragment()
            fragment.arguments = Bundle.EMPTY
            return fragment
        }
    }

    private lateinit var reposPresenter: ReposContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reposPresenter = ReposProvider.instance.providePresenter()
        reposPresenter.view = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        rv.layoutManager = LinearLayoutManager(activity)
        setSearchView()
    }

    override fun onDestroy() {
        super.onDestroy()
        reposPresenter.onDestroyed()
    }

    override fun showEmptyView() {
        tvEmpty?.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        tvEmpty?.visibility = View.GONE
    }

    override fun clearItems() {
        (rv.adapter as? RvRepoAdapter)?.clear()
    }

    override fun setSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            val handler = Handler()

            override fun onQueryTextSubmit(query: String?): Boolean {
                reposPresenter.onSearchCalled(query ?: "")
                GlobalUtils.switchOfTheKeyBoard(searchView)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    onQueryTextSubmit(newText)
                }, 500)
                return true
            }

        })
        searchView.setOnCloseListener {
            reposPresenter.onSearchCancelled()
            true
        }
    }

    override fun startProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun stopProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun updateViewWithData(repositories: List<RepoItem>) {
        if (rv.adapter == null) {
            rv.adapter = RvRepoAdapter(repositories)
        } else {
            (rv.adapter as RvRepoAdapter).changeDataSet(repositories)
        }
    }
}