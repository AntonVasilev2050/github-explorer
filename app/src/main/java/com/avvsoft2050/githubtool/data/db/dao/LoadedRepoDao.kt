package com.avvsoft2050.githubtool.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avvsoft2050.githubtool.data.db.entity.LoadedRepo
import kotlinx.coroutines.flow.Flow

@Dao
interface LoadedRepoDao {

    @Query("SELECT * FROM loaded_repo_table ORDER BY repo_name DESC")
    fun getLoadedRepos(): Flow<List<LoadedRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(loadedRepo: LoadedRepo)
}