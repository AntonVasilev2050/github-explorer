package com.avvsoft2050.githubtool.data

import com.avvsoft2050.githubtool.data.api.ApiFactory
import com.avvsoft2050.githubtool.data.db.dao.LoadedRepoDao
import com.avvsoft2050.githubtool.data.db.entity.LoadedRepo
import com.avvsoft2050.githubtool.domain.repository.ReposRepository
import com.avvsoft2050.githubtool.data.model.OwnerRepo
import kotlinx.coroutines.flow.Flow

class ReposRepositoryImp(private val loadedRepoDao: LoadedRepoDao) : ReposRepository {
    override val allLoadedRepos: Flow<List<LoadedRepo>>
        get() = loadedRepoDao.getLoadedRepos()

    override suspend fun insert(loadedRepo: LoadedRepo) {
        loadedRepoDao.insert(loadedRepo)
    }

    override suspend fun getUserRepos(user: String): MutableList<OwnerRepo> {
        return ApiFactory.apiService.getRepos(user)
    }
}