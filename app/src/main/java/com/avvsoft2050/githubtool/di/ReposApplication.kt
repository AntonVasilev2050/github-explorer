package com.avvsoft2050.githubtool.di

import android.app.Application
import com.avvsoft2050.githubtool.data.ReposRepositoryImp
import com.avvsoft2050.githubtool.data.db.LoadedRepoDatabase

class ReposApplication: Application() {
    val database by lazy { LoadedRepoDatabase.getDatabase(this) }
    val repository by lazy { ReposRepositoryImp(database.loadedRepoDao()) }
}