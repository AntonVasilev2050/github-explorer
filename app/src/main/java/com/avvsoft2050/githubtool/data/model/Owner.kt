package com.avvsoft2050.githubtool.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Owner (
    @SerializedName("login")
    @Expose
    val login: String?,

    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String?,

    @SerializedName("gravatar_id")
    @Expose
    val gravatarId: String?,

    @SerializedName("html_url")
    @Expose
    val htmlUrl: String?
)