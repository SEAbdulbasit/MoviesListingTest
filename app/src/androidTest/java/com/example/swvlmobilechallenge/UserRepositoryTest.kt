package com.example.swvlmobilechallenge

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.swvlmobilechallenge.apiservices.UserRepository
import com.example.swvlmobilechallenge.apiservices.createRetrofit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {

    private val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private var userRepository: UserRepository? = null

    @Before
    fun initialize() {
        userRepository = UserRepository(createRetrofit())
    }

    @Test
    fun retrievedMoviesListSuccessfully_Success() {
        val result = userRepository?.getMoviesList(appContext)
        assertEquals(result?.movies?.size ?: 0, 2272)
    }

    @Test
    fun movieSizeNotSatisfied_Error() {
        val result = userRepository?.getMoviesList(appContext)
        assertNotEquals(result?.movies?.size ?: 0, -2272)
    }

    @Test
    fun checkFirstItem_Success() {
        val result = userRepository?.getMoviesList(appContext)
        assertEquals(result?.movies?.get(0)?.title, "(500) Days of Summer")
    }

    @Test
    fun checkFirstItem_Error() {
        val result = userRepository?.getMoviesList(appContext)
        assertNotEquals(result?.movies?.get(0)?.title, "(500) Days of Summer")
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.swvlmobilechallenge", appContext.packageName)
    }
}