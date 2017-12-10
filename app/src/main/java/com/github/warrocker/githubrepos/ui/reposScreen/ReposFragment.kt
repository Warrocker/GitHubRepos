package com.github.warrocker.githubrepos.ui.reposScreen

import android.app.Fragment
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.warrocker.githubrepos.R
import com.github.warrocker.githubrepos.core.ActivityContextKeeper
import com.github.warrocker.githubrepos.core.GlobalUtils
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.ui.reposScreen.adapters.RvRepoAdapter
import kotlinx.android.synthetic.main.fragment_user_repo.*
import kotlinx.android.synthetic.main.header_small.*

/**
 * Created by Warrocker on 10.12.2017.
 */
class ReposFragment : Fragment(), IReposView {
    override fun setEmptyView(visible : Boolean) {
        if(visible)
        tvEmpty?.visibility =  View.VISIBLE
        else
        tvEmpty?.visibility =  View.GONE
    }

    override fun clearItems() {
        if(rv.adapter != null)
        (rv.adapter as RvRepoAdapter).clear()
    }

    override fun setCancelView() {
        ivSearch.setImageDrawable(ActivityContextKeeper.instance?.context?.getMainContext()?.let { ContextCompat.getDrawable(it, R.drawable.clear) })
        ivSearch.setOnClickListener({
                reposPresenter?.onSearchCancelled()
        })
    }

    override fun setSearchView() {
        ivSearch.setImageDrawable(ActivityContextKeeper.instance?.context?.getMainContext()?.let { ContextCompat.getDrawable(it, R.drawable.search) })
        ivSearch.setOnClickListener({
            val searchString = etSearch.text.toString().trim()
            if (!TextUtils.isEmpty(searchString)) {
                reposPresenter?.onSearchCalled(searchString)
                GlobalUtils.switchOfTheKeyBoard(it)
            }

        })
    }

    override fun startProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun stopProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun updateViewWithData(repositories: List<RepoItem>) {
        if (rv.adapter == null) {
            rv.adapter = repositories.let { RvRepoAdapter(it) }
        } else {
            repositories.let { (rv.adapter as RvRepoAdapter).changeDataSet(it) }
        }
    }

    var reposPresenter: IReposPresenter? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_user_repo, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        rv.layoutManager = LinearLayoutManager(activity)
        setSearchView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        reposPresenter?.onSearchCancelled()
    }

}