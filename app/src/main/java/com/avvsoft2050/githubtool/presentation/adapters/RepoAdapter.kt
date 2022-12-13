package com.avvsoft2050.githubtool.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avvsoft2050.githubtool.data.model.OwnerRepo
import com.avvsoft2050.githubtool.databinding.ItemRepoBinding

class RepoAdapter(
    private val onClickAction: (OwnerRepo) -> Unit
) : ListAdapter<OwnerRepo, RepoAdapter.RepoViewHolder>(ReposDiffUtil()) {


    inner class RepoViewHolder(val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ownerRepo: OwnerRepo) {
            binding.textViewRepoName.text = ownerRepo.name
        }
    }

    class ReposDiffUtil : DiffUtil.ItemCallback<OwnerRepo>() {
        override fun areItemsTheSame(oldItem: OwnerRepo, newItem: OwnerRepo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: OwnerRepo, newItem: OwnerRepo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(inflater)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            onClickAction(item)
        }
    }
}