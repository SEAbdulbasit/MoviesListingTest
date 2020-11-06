package com.example.swvlmobilechallenge.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swvlmobilechallenge.BaseViewModel
import com.example.swvlmobilechallenge.apiservices.Status
import com.example.swvlmobilechallenge.ui.main.MovieResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MovieDetailsViewModel(val movie: MovieResponseModel.Movie) : BaseViewModel() {

    val imagesList = MutableLiveData<List<FlickrSearchResponseModel.Photos.Photo>>()

    init {
        coroutineScope.launch {

            Timber.d("Movie tittle ${movie.title}")

            val result = userRepository.getMovieImages(movieTittle = movie.title!!)
            withContext(Dispatchers.Main) {
                when (result.status) {
                    Status.SUCCESS -> {
                        imagesList.value = result.data?.photos?.photo
                    }
                    else -> {
                    }
                }
            }
        }
    }


    class Factory(val movie: MovieResponseModel.Movie) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailsViewModel(movie = movie) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}