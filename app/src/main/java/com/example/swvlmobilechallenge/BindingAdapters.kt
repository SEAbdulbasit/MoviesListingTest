package com.example.swvlmobilechallenge

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.swvlmobilechallenge.ui.main.MovieResponseModel
import com.example.swvlmobilechallenge.ui.main.MoviesAdapter
import timber.log.Timber

//
// Created by Abdul Basit on 11/6/2020.
//

@BindingAdapter("submitMovies")
fun subMovies(
    view: RecyclerView,
    list: List<MovieResponseModel.Movie>?
) {
    (view.adapter as MoviesAdapter).submitList(list)

}

@BindingAdapter("loadImageFromURL")
fun loadImage(
    imgView: ImageView,
    photoModel
    : com.example.swvlmobilechallenge.ui.details.FlickrSearchResponseModel.Photos.Photo?
) {

    val circularProgressDrawable = CircularProgressDrawable(imgView.context)
    circularProgressDrawable.strokeWidth = 7f
    circularProgressDrawable.centerRadius = 40f

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        circularProgressDrawable.colorFilter =
            BlendModeColorFilter(
                ContextCompat.getColor(imgView.context, R.color.purple_200),
                BlendMode.SRC_IN
            )
    } else {
        circularProgressDrawable.setColorFilter(
            ContextCompat.getColor(
                imgView.context,
                R.color.purple_200
            ), PorterDuff.Mode.SRC_IN
        )
    }


    val imageUrl =
        "https://farm${
            photoModel
                ?.farm
        }.static.flickr.com/${
            photoModel
                ?.server
        }/${
            photoModel
                ?.id
        }_${
            photoModel
                ?.secret
        }.jpg"

    Timber.d("Image URL $imageUrl")

    // circularProgressDrawable.start()
    Glide.with(imgView.context)
        .load(
            imageUrl
        )
        .placeholder(circularProgressDrawable)
        .into(imgView)
}