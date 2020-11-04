package com.example.swvlmobilechallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swvlmobilechallenge.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : BaseViewModel() {

    private var moviesList: List<MovieResponseModel.Movie>? = null
    val moviesLiveList = MutableLiveData<List<MovieResponseModel.Movie>>()

    init {
        coroutineScope.launch(Dispatchers.IO) {
            moviesList = userRepository.getMoviesList()?.movies
            withContext(Dispatchers.Main) {
                moviesLiveList.value = moviesList
            }
        }
    }

    fun filterData(date: String?) {

        if (!date?.trim().isNullOrBlank()) {
            coroutineScope.launch(Dispatchers.IO) {
                val list = moviesList?.filter { it.year.toString().contains(date!!) }
                    ?.sortedByDescending { it.rating }
                    ?.let {
                        it.subList(
                            0, if (it.size >= 5) {
                                5
                            } else {
                                it.size
                            }
                        )
                    }
                withContext(Dispatchers.Main) {
                    moviesLiveList.value = list
                }

            }
        } else {
            moviesLiveList.value = moviesList
        }
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel() as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }

}