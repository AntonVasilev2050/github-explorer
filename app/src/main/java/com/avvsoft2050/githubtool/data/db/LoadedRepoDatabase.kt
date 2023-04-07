package com.avvsoft2050.githubtool.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.avvsoft2050.githubtool.data.db.dao.LoadedRepoDao
import com.avvsoft2050.githubtool.data.db.entity.LoadedRepo

@Database(entities = arrayOf(LoadedRepo::class), version = 1, exportSchema = false)
abstract class LoadedRepoDatabase : RoomDatabase() {

    abstract fun loadedRepoDao(): LoadedRepoDao

    companion object {
        @Volatile
        private var INSTANCE: LoadedRepoDatabase? = null

        fun getDatabase(context: Context): LoadedRepoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoadedRepoDatabase::class.java,
                    "loaded_repo_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}