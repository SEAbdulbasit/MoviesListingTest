package com.example.swvlmobilechallenge.baseclasses

import androidx.lifecycle.ViewModel
import com.example.swvlmobilechallenge.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
    private val viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    var context = App.getInstance()
}
