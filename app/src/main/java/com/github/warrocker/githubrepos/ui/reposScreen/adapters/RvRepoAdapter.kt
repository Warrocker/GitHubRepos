package com.github.warrocker.githubrepos.ui.reposScreen.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.warrocker.githubrepos.R
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import kotlinx.android.synthetic.main.item_repo.view.*

/**
 * Created by Warrocker on 10.12.2017.
 */
class RvRepoAdapter(items: List<RepoItem>) : RecyclerView.Adapter<RvRepoAdapter.ViewHolder>() {
    private var items: ArrayList<RepoItem> = ArrayList()

    init {
        this.items.addAll(items)
    }

    fun changeDataSet(list: List<RepoItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
        notifyItemChanged(1)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindRepos(repoItem: RepoItem) {
            itemView.tvTitle?.text = repoItem.fullName
            itemView.tvDesc?.text = repoItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return RvRepoAdapter.ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepos(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }
}