package com.github.warrocker.githubrepos.core.entity.reposentities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName



/**
 * Created by Warrocker on 10.12.2017.
 */
@Entity(tableName = "repoItem")
class RepoItem {
    @PrimaryKey(autoGenerate = true)
    var databaseId: Int? = null
    @SerializedName("id")
    var id: Int? = null
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null
    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    var fullName: String? = null
    @ColumnInfo(name = "private")
    @SerializedName("private")
    var _private: Boolean? = null
    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    var htmlUrl: String? = null
    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null
    @ColumnInfo(name = "fork")
    @SerializedName("fork")
    var fork: Boolean? = null
    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String? = null
    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    var createdAt: String? = null
    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @ColumnInfo(name = "pushed_at")
    @SerializedName("pushed_at")
    var pushedAt: String? = null
    @ColumnInfo(name = "homepage")
    @SerializedName("homepage")
    var homepage: String? = null
    @ColumnInfo(name = "size")
    @SerializedName("size")
    var size: Int? = null
    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    var stargazersCount: Int? = null
    @ColumnInfo(name = "watchers_count")
    @SerializedName("watchers_count")
    var watchersCount: Int? = null
    @ColumnInfo(name = "language")
    @SerializedName("language")
    var language: String? = null
    @ColumnInfo(name = "forks_count")
    @SerializedName("forks_count")
    var forksCount: Int? = null
    @ColumnInfo(name = "open_issues_count")
    @SerializedName("open_issues_count")
    var openIssuesCount: Int? = null
    @ColumnInfo(name = "master_branch")
    @SerializedName("master_branch")
    var masterBranch: String? = null
    @ColumnInfo(name = "default_branch")
    @SerializedName("default_branch")
    var defaultBranch: String? = null
    @ColumnInfo(name = "score")
    @SerializedName("score")
    var score: Float? = null
}