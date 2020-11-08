package com.example.swvlmobilechallenge.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swvlmobilechallenge.apiservices.Status
import com.example.swvlmobilechallenge.apiservices.UserRepository
import com.example.swvlmobilechallenge.baseclasses.BaseViewModel
import com.example.swvlmobilechallenge.ui.main.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MovieDetailsViewModel(val userRepository: UserRepository, val movie: Movie) :
    BaseViewModel() {

    val imagesList = MutableLiveData<List<FlickrSearchResponseModel.Photos.Photo>>()

    init {
        coroutineScope.launch {
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


    class Factory(private val userRepository: UserRepository, val movie: Movie) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailsViewModel(userRepository = userRepository, movie = movie) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}