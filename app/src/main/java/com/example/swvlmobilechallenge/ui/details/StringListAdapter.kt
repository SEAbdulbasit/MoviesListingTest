package com.example.swvlmobilechallenge.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swvlmobilechallenge.R
import com.example.swvlmobilechallenge.databinding.HolderGenreBinding

//
// Created by Abdul Basit on 11/4/2020.
//


class StringListAdapter internal constructor(
) : ListAdapter<String, RecyclerView.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<HolderGenreBinding>(
            LayoutInflater.from(parent.context),
            R.layout.holder_genre,
            parent,
            false
        )

        return MovieHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieHolder).bind(getItem(position))

    }


    inner class MovieHolder internal constructor(private val binding: HolderGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(addressTag: String) {
            binding.value = addressTag
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }
}
