package com.avvsoft2050.githubtool.domain.repository

import androidx.annotation.WorkerThread
import com.avvsoft2050.githubtool.data.db.entity.LoadedRepo
import com.avvsoft2050.githubtool.data.model.OwnerRepo
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    val  allLoadedRepos : Flow<List<LoadedRepo>>

    @WorkerThread
    suspend fun insert(loadedRepo: LoadedRepo)
    suspend fun getUserRepos(user: String): MutableList<OwnerRepo>
}