package com.github.warrocker.githubrepos.core.entity.reposentities

import com.google.gson.annotations.SerializedName



/**
 * Created by Warrocker on 10.12.2017.
 */
class RepoOwner {
    @SerializedName("login")
    var login: String? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
    @SerializedName("gravatar_id")
    var gravatarId: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = null
    @SerializedName("type")
    var type: String? = null
}