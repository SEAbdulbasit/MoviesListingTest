package com.example.swvlmobilechallenge.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swvlmobilechallenge.R
import com.example.swvlmobilechallenge.databinding.HolderImagesBinding

//
// Created by Abdul Basit on 11/4/2020.
//


class ImagesAdapter internal constructor(
) : ListAdapter<FlickrSearchResponseModel.Photos.Photo, RecyclerView.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<HolderImagesBinding>(
            LayoutInflater.from(parent.context),
            R.layout.holder_images,
            parent,
            false
        )

        return MovieHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieHolder).bind(getItem(position))
    }


    inner class MovieHolder internal constructor(private val binding: HolderImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: FlickrSearchResponseModel.Photos.Photo) {
            binding.value = photo
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack :
        DiffUtil.ItemCallback<FlickrSearchResponseModel.Photos.Photo>() {
        override fun areItemsTheSame(
            oldItem: FlickrSearchResponseModel.Photos.Photo,
            newItem: FlickrSearchResponseModel.Photos.Photo
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FlickrSearchResponseModel.Photos.Photo,
            newItem: FlickrSearchResponseModel.Photos.Photo
        ): Boolean {
            return oldItem == newItem
        }
    }
}
