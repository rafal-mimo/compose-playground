package com.example.compose.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor() : ViewModel() {

    var counter = 0

    init {
        viewModelScope.launch {
            while (isActive) {
                delay(500)
                usernameLiveData.postValue(counter++.toString())
                usernameFlow.value = counter.toString()
            }
        }
    }

    val usernameFlow = MutableStateFlow("")
    val usernameLiveData = MutableLiveData<String>()
}
