package com.avvsoft2050.githubtool.data.api

import com.avvsoft2050.githubtool.domain.model.OwnerRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{user}/repos")
    suspend fun getRepos(
        @Path("user") user: String
    ): MutableList<OwnerRepo>
}