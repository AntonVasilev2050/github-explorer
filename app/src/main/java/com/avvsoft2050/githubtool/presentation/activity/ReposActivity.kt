package com.avvsoft2050.githubtool.presentation.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.avvsoft2050.githubtool.R
import com.avvsoft2050.githubtool.data.db.entity.LoadedRepo
import com.avvsoft2050.githubtool.data.model.OwnerRepo
import com.avvsoft2050.githubtool.databinding.ActivityReposBinding
import com.avvsoft2050.githubtool.databinding.DialogRepoDetailsBinding
import com.avvsoft2050.githubtool.di.ReposApplication
import com.avvsoft2050.githubtool.presentation.adapters.RepoAdapter
import com.avvsoft2050.githubtool.presentation.viewmodel.ReposViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class ReposActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReposBinding
    private val viewModel: ReposViewModel by viewModels {
        ReposViewModel.ReposViewModelFactory((application as ReposApplication).repository)
    }
    private lateinit var repoAdapter: RepoAdapter
    private lateinit var ownerRepo: OwnerRepo
    private val startForResult = registerForActivityResult(StartActivityForResult()){
    val ownerName = ownerRepo.owner.login
    val repoName = ownerRepo.name
    val loadedRepo = LoadedRepo(null, ownerName.toString(), repoName)
    viewModel.insert(loadedRepo)
    Toast.makeText(this, getString(R.string.repo_data_saved), Toast.LENGTH_LONG).show()
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReposBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repoAdapter = RepoAdapter(
            onClickAction = {
                showRepoDetails(it)
                ownerRepo = it
            }
        )
        binding.recyclerViewRepos.adapter = repoAdapter
        setupActions()
        setupObservers()
    }

    private fun showRepoDetails(ownerRepo: OwnerRepo) {
        val repoDetails = BottomSheetDialog(this)
        val bindingRepoDetails = DialogRepoDetailsBinding.inflate(layoutInflater)
        repoDetails.setContentView(bindingRepoDetails.root)
        with(bindingRepoDetails) {
            textViewDetailsRepoName.text = ownerRepo.name
            textViewDialogRepoLink.text = ownerRepo.htmlUrl
            buttonDialogOpenLink.setOnClickListener {
                openLink(ownerRepo.htmlUrl)
            }
            buttonDialogDownloadRepo.setOnClickListener {
                downloadZip(ownerRepo)
            }
        }
        repoDetails.show()
    }

    private fun downloadZip(ownerRepo: OwnerRepo) {
        val zipUrl = buildString {
            append(ownerRepo.htmlUrl)
            append("/archive/refs/heads/")
            append(ownerRepo.defaultBranch)
            append(".zip")
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(zipUrl))
        if (intent.resolveActivity(packageManager) != null) {
            startForResult.launch(intent)
        } else {
            Log.d("ImplicitIntents", getString(R.string.cant_handle_this))
        }

    }

    private fun openLink(htmlUrl: String?) {
        val webpage = Uri.parse(htmlUrl)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", getString(R.string.cant_handle_this))
        }
    }

    private fun setupActions() {
        binding.buttonSearchRepos.setOnClickListener {
            val user = binding.editTextTextUserName.text.toString().trim()
            viewModel.viewModelScope.launch {
                viewModel.onSearchButtonClicked(user)
            }
        }
    }

    private fun setupObservers() {
        viewModel.repoListLiveData.observe(this) {
            if (it.size > 0) {
                Picasso.get().load(it[0].owner.avatarUrl).into(binding.imageViewUserAvatar)
                binding.textViewUserLoginLabel.text = buildString {
                    append(it[0].owner.login ?: getString(R.string.unknown_user))
                    append(" repositories:")
                }
            }
            repoAdapter.submitList(it)
        }
        viewModel.infoMessageLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val clickedItem = item.itemId
        when(clickedItem){
            R.id.action_show_downloaded_repos -> {
                val intent = Intent(this, LoadedReposActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}