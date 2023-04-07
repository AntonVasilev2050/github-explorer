package com.avvsoft2050.githubtool.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.avvsoft2050.githubtool.databinding.ActivityLoadedReposBinding
import com.avvsoft2050.githubtool.di.ReposApplication
import com.avvsoft2050.githubtool.presentation.adapters.LoadedReposAdapter
import com.avvsoft2050.githubtool.presentation.viewmodel.ReposViewModel

class LoadedReposActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadedReposBinding
    private val viewModel: ReposViewModel by viewModels {
        ReposViewModel.ReposViewModelFactory((application as ReposApplication).repository)
    }
    private lateinit var loadedReposAdapter: LoadedReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadedReposBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        loadedReposAdapter = LoadedReposAdapter()
        binding.recyclerviewLoadedRepos.adapter = loadedReposAdapter
        viewModel.allLoadedRepos.observe(this) {
            loadedReposAdapter.submitList(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}