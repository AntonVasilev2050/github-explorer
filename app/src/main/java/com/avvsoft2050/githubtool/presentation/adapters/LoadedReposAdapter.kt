package com.avvsoft2050.githubtool.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avvsoft2050.githubtool.data.db.entity.LoadedRepo
import com.avvsoft2050.githubtool.databinding.ItemLoadedRepoBinding

class LoadedReposAdapter(

) : ListAdapter<LoadedRepo, LoadedReposAdapter.LoadedReposViewHolder>(LoadedReposDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadedReposViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLoadedRepoBinding.inflate(inflater)
        return LoadedReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadedReposViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class LoadedReposViewHolder(val binding: ItemLoadedRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadedRepo: LoadedRepo) {
            binding.textViewOwnerName.text = loadedRepo.ownerName
            binding.textViewLoadedRepoName.text = loadedRepo.repoName
        }
    }


    class LoadedReposDiffUtil : DiffUtil.ItemCallback<LoadedRepo>() {
        override fun areItemsTheSame(oldItem: LoadedRepo, newItem: LoadedRepo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: LoadedRepo, newItem: LoadedRepo): Boolean {
            return oldItem.id == newItem.id
        }

    }
}