package com.example.swvlmobilechallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swvlmobilechallenge.App
import com.example.swvlmobilechallenge.apiservices.MoviesRepository
import com.example.swvlmobilechallenge.baseclasses.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val LAYOUT_TYPE_HEADER = 0
const val LAYOUT_TYPE_ITEM = 1

class MainViewModel(val moviesRepository: MoviesRepository) : BaseViewModel() {

    private var moviesList: List<MovieResponseModel.Movie>? = null
    val moviesLiveList = MutableLiveData<List<MovieResponseModel.Movie>>()

    fun getMoviesList() {
        coroutineScope.launch(Dispatchers.IO) {
            moviesList = moviesRepository.getMoviesList(App.getInstance())?.movies
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

        //filtering and grouping the data based on movie tittle
        val list = moviesList?.filter {
            it.title.toString().contains(searchName, ignoreCase = true)
        }
            ?.groupBy { it.year }

        val resultedList = mutableListOf<MovieResponseModel.Movie>()

        // as we have data in group form and we also need to show year tittle again each group so
        // we will be adding header items along with searched items
        if (list != null) {
            for ((k, v) in list) {
                resultedList.add(MovieResponseModel.Movie(isHeader = true, year = k))
                resultedList.addAll(v.toList().sortedByDescending { it.rating }
                    .subList(0, if (v.size >= 5) 5 else v.size))
            }
        }

        return resultedList
    }

    class Factory(private val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(moviesRepository = moviesRepository) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }

}