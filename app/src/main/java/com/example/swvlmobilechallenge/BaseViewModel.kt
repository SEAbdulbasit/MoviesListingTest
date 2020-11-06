package com.example.swvlmobilechallenge

import androidx.lifecycle.AndroidViewModel
import com.example.swvlmobilechallenge.apiservices.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel : AndroidViewModel(App.getInstance()) {
    val userRepository: UserRepository = App.getInstance().getUserRepository()
    private val viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    var context = App.getInstance()
}
