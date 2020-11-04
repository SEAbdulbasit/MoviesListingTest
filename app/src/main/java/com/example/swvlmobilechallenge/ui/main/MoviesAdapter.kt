package com.example.swvlmobilechallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swvlmobilechallenge.R
import com.example.swvlmobilechallenge.databinding.HolderMovieBinding

//
// Created by Abdul Basit on 11/4/2020.
//


class MoviesAdapter internal constructor(
) : ListAdapter<MovieResponseModel.Movie, MoviesAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<HolderMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.holder_movie,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))

    }

    inner class ViewHolder internal constructor(private val binding: HolderMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(addressTag: MovieResponseModel.Movie) {
            binding.model = addressTag
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<MovieResponseModel.Movie>() {
        override fun areItemsTheSame(
            oldItem: MovieResponseModel.Movie,
            newItem: MovieResponseModel.Movie
        ): Boolean {
            return false//oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieResponseModel.Movie,
            newItem: MovieResponseModel.Movie
        ): Boolean {
            return false//oldItem.title == newItem.title
        }
    }
}
