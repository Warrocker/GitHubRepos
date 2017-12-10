package com.github.warrocker.githubrepos.core.entity.reposentities

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName



/**
 * Created by Warrocker on 10.12.2017.
 */
@Entity
class Repositories {
    @SerializedName("total_count")
    var totalCount: Int? = null
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean? = null
    @SerializedName("items")
    var items: List<RepoItem>? = null
}