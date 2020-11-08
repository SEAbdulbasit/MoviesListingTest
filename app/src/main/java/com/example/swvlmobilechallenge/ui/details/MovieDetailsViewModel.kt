package com.example.swvlmobilechallenge.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swvlmobilechallenge.App
import com.example.swvlmobilechallenge.R
import com.example.swvlmobilechallenge.apiservices.MoviesRepository
import com.example.swvlmobilechallenge.apiservices.Status
import com.example.swvlmobilechallenge.baseclasses.BaseViewModel
import com.example.swvlmobilechallenge.ui.main.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(val moviesRepository: MoviesRepository, val movie: Movie) :
    BaseViewModel() {

    val imagesList = MutableLiveData<List<FlickrSearchResponseModel.Photos.Photo>>()
    val errorMessage = MutableLiveData<String>()

    init {
        getMovieImages(movie.title!!)
    }

    // make API call to get images related to the movie tittle
    private fun getMovieImages(tittle: String) {
        coroutineScope.launch {
            val result = moviesRepository.getMovieImages(movieTittle = tittle)
            withContext(Dispatchers.Main) {
                when (result.status) {
                    Status.SUCCESS -> {
                        imagesList.value = result.data?.photos?.photo
                    }
                    Status.NO_INTERNET_CONNECTION -> {
                        errorMessage.value =
                            App.getInstance().getString(R.string.no_internet_connection)
                    }
                    else -> {
                        errorMessage.value =
                            App.getInstance().getString(R.string.something_went_wrong)

                    }
                }
            }
        }
    }


    class Factory(private val moviesRepository: MoviesRepository, val movie: Movie) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailsViewModel(
                    moviesRepository = moviesRepository,
                    movie = movie
                ) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}