package com.example.swvlmobilechallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swvlmobilechallenge.R
import com.example.swvlmobilechallenge.databinding.HolderHeaderBinding
import com.example.swvlmobilechallenge.databinding.HolderMovieBinding

//
// Created by Abdul Basit on 11/4/2020.
//


//using diff utils for better optimization
class MoviesAdapter internal constructor(
    private val callback: (MovieResponseModel.Movie) -> Unit
) : ListAdapter<MovieResponseModel.Movie, RecyclerView.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == LAYOUT_TYPE_ITEM) {
            val binding = DataBindingUtil.inflate<HolderMovieBinding>(
                LayoutInflater.from(parent.context),
                R.layout.holder_movie,
                parent,
                false
            )

            return MovieHolder(binding)
        } else {
            val binding = DataBindingUtil.inflate<HolderHeaderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.holder_header,
                parent,
                false
            )
            return HeaderHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item.isHeader) {
            (holder as HeaderHolder).bind(getItem(position))

        } else {
            (holder as MovieHolder).bind(getItem(position))
            holder.itemView.setOnClickListener { callback.invoke(getItem(position)) }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isHeader) return LAYOUT_TYPE_HEADER else LAYOUT_TYPE_ITEM
    }

    inner class MovieHolder internal constructor(private val binding: HolderMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponseModel.Movie) {
            binding.model = movie
            binding.executePendingBindings()
        }
    }

    inner class HeaderHolder internal constructor(private val binding: HolderHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponseModel.Movie) {
            binding.model = movie
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<MovieResponseModel.Movie>() {
        override fun areItemsTheSame(
            oldItem: MovieResponseModel.Movie,
            newItem: MovieResponseModel.Movie
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MovieResponseModel.Movie,
            newItem: MovieResponseModel.Movie
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
}
