package com.example.swvlmobilechallenge

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.swvlmobilechallenge.apiservices.MoviesRepository
import com.example.swvlmobilechallenge.apiservices.createRetrofit
import com.example.swvlmobilechallenge.ui.main.MainViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

//
// Created by Abdul Basit on 11/7/2020.
//

class MainViewModelTest : TestCase() {
    private val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val userRepository = MoviesRepository(createRetrofit())
    private lateinit var mainViewMovie: MainViewModel

    @Before
    public override fun setUp() {
        super.setUp()
        mainViewMovie = MainViewModel(userRepository)
        mainViewMovie.getMoviesList()
    }

    @Test
    fun testGetMoviesLiveList_Success() {
        val results = mainViewMovie.moviesRepository.getMoviesList(appContext)
        assertTrue(results?.movies?.isNotEmpty() ?: false)
    }

    @Test
    fun testFor2Results_Success() {

        //making sure that list is prepared before filtering
        Thread.sleep(4000)
        val searchResults = mainViewMovie.filterDataOnSearch("Amelia")

        //we have 2 results as we are also adding headers to the list
        assertEquals(2, searchResults.size)

    }

    @Test
    fun noResultsFound_Error() {

        //making sure that list is prepared before filtering
        Thread.sleep(4000)
        val searchResults = mainViewMovie.filterDataOnSearch("Amelia")

        //we have 2 results as we are also adding headers to the list
        assert(searchResults.isNotEmpty())

    }

    @Test
    fun testOnlyFiveItemsAreShownAgainstYear_Success() {

        //making sure that list is prepare before filtering
        Thread.sleep(4000)
        val searchResults = mainViewMovie.filterDataOnSearch("al")
        val year = searchResults[0].year
        val groupedByYear = searchResults.groupBy { it.year == year }
        assertEquals(true, groupedByYear[year]?.size ?: 0 <= 5)

    }

}