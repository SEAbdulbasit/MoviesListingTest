package com.example.swvlmobilechallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swvlmobilechallenge.App
import com.example.swvlmobilechallenge.apiservices.UserRepository
import com.example.swvlmobilechallenge.baseclasses.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val LAYOUT_TYPE_HEADER = 0
const val LAYOUT_TYPE_ITEM = 1

class MainViewModel(val userRepository: UserRepository) : BaseViewModel() {

    private var moviesList: List<MovieResponseModel.Movie>? = null
    val moviesLiveList = MutableLiveData<List<MovieResponseModel.Movie>>()

    fun getMoviesList() {
        coroutineScope.launch(Dispatchers.IO) {
            moviesList = userRepository.getMoviesList(App.getInstance())?.movies
            withContext(Dispatchers.Main) {
                moviesLiveList.value = moviesList
            }
        }
    }

    fun filterData(date: String?) {
        if (!date?.trim().isNullOrBlank()) {
            coroutineScope.launch(Dispatchers.IO) {
                val resultedList = filterDataOnSearch(date!!)
                withContext(Dispatchers.Main) {
                    moviesLiveList.value = resultedList
                }
            }
        } else {
            moviesLiveList.value = moviesList
        }
    }

    fun filterDataOnSearch(searchName: String): MutableList<MovieResponseModel.Movie> {
        val list = moviesList?.filter {
            it.title.toString().contains(searchName, ignoreCase = true)
        }
            ?.groupBy { it.year }

        val resultedList = mutableListOf<MovieResponseModel.Movie>()
        if (list != null) {
            for ((k, v) in list) {
                resultedList.add(MovieResponseModel.Movie(isHeader = true, year = k))
                resultedList.addAll(v.toList().sortedByDescending { it.rating }
                    .subList(0, if (v.size >= 5) 5 else v.size))
            }
        }

        return resultedList
    }

    class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(userRepository = userRepository) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }

}