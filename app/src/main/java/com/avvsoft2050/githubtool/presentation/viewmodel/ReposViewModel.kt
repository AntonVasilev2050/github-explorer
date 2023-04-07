package com.avvsoft2050.githubtool.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.avvsoft2050.githubtool.domain.entity.LoadedRepo
import com.avvsoft2050.githubtool.domain.repository.ReposRepository
import com.avvsoft2050.githubtool.domain.model.OwnerRepo
import kotlinx.coroutines.*

class ReposViewModel(
    private val repository: ReposRepository
) : ViewModel() {

    private var repoList = mutableListOf<OwnerRepo>()
    private val _repoListLiveData = MutableLiveData(repoList)
    val repoListLiveData: LiveData<MutableList<OwnerRepo>> get() = _repoListLiveData
    private var infoMessage = "Hi there!"
    private val _infoMessageLiveData = MutableLiveData(infoMessage)
    val infoMessageLiveData: LiveData<String> get() = _infoMessageLiveData

    val allLoadedRepos: LiveData<List<LoadedRepo>> = repository.allLoadedRepos.asLiveData()
    suspend fun onSearchButtonClicked(user: String) {
        infoMessage = ""
        val result = viewModelScope.launch {
            try {
                repoList = repository.getUserRepos(user)
                _repoListLiveData.value = repoList
                infoMessage = "Data loaded"
                _infoMessageLiveData.value = infoMessage
            } catch (e: Exception) {
                infoMessage = e.message.toString()
                Log.d("TEST_OF_LOADING_DATA", "Error: ${e.message.toString()}")
            }

        }
//        result.join()
//        if (_repoListLiveData.value != repoList) {
//            _repoListLiveData.value = repoList
//        }
//        _infoMessageLiveData.value = infoMessage
    }


    fun insert(loadedRepo: LoadedRepo) = viewModelScope.launch {
        repository.insert(loadedRepo)
    }

    class ReposViewModelFactory(
        private val repository: ReposRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ReposViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}