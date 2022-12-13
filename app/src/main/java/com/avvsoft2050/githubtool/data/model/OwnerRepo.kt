package com.avvsoft2050.githubtool.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class OwnerRepo (
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("owner")
    @Expose
    val owner: Owner,

    @SerializedName("html_url")
    @Expose
    val htmlUrl: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("created_at")
    @Expose
    val createdAt: String?,

    @SerializedName("updated_at")
    @Expose
    val updatedAt: String?,

    @SerializedName("pushed_at")
    @Expose
    val pushedAt: String?,

    @SerializedName("visibility")
    @Expose
    val visibility: String?,

    @SerializedName("default_branch")
    @Expose
    val defaultBranch: String?
)