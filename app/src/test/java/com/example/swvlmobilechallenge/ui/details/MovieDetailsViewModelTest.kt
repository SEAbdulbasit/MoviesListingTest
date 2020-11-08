package com.example.swvlmobilechallenge.ui.details

import com.example.swvlmobilechallenge.BASE_URL
import com.example.swvlmobilechallenge.apiservices.AppRetrofit
import com.example.swvlmobilechallenge.apiservices.MoviesRepository
import com.example.swvlmobilechallenge.apiservices.moshi
import com.example.swvlmobilechallenge.ui.main.Movie
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//
// Created by Abdul Basit on 11/8/2020.
//

class MovieDetailsViewModelTest {

    private fun createRetrofit(): AppRetrofit {
        val coreRetrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(makeHttpClient())
            .baseUrl(BASE_URL)
            .build()
        return coreRetrofit.create(AppRetrofit::class.java)
    }

    private fun makeHttpClient(): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        okBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okBuilder.readTimeout(30, TimeUnit.SECONDS)
        okBuilder.addInterceptor(MockInterceptors())
        okBuilder.addNetworkInterceptor(StethoInterceptor())

        return okBuilder.build()
    }

    private val movieModel = Movie(
        title = "(500) Days of Summer",
        year = 2009,
        cast = "Joseph Gordon-Levitt",
        genres = "Romance",
        rating = 1
    )

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private val repositoryHelperClass = MoviesRepository(createRetrofit())

    @Before
    fun setUp() {
        movieDetailsViewModel = MovieDetailsViewModel(repositoryHelperClass, movieModel)
    }

    @Test
    fun getMovieDetails_Success() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = movieDetailsViewModel.moviesRepository.getMovieImages(movieModel.title!!)
            assertEquals(response.data != null, true)
        }
    }


}

