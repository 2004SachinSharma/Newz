package com.example.newz.presentation.NewzViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newz.data.ApiBuilder.ApiBuilder.ApiBuilder
import com.example.newz.data.ApiState
import com.example.newz.data.NewzRepo
import com.example.newz.data.model.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewzViewModel :
    ViewModel() { // For making view model first inherit from ViewModel as done in this line
    val repo = NewzRepo()
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()

    init {
        getHeadLines()
    }

    fun getHeadLines(country: String = "us") {
        viewModelScope.launch {
            repo.getHeadLine(country).collectLatest {
                if (it.loading == true) {
                    _state.value = AppState(loading = true)
                } else if (it.error.isNullOrBlank().not()) {
                    _state.value = AppState(error = it.error)
                } else {
                    _state.value = AppState(data = it.data, loading = false)

                }
            }
        }
    }


}
// Now for managing the state of full screen , we will create a class followed by any name, lets say AppState

data class AppState(
    //Now we will define all the possibilities here ,by which the app might go through...
    var loading: Boolean? = null, // Now this is obvious that the app might be loading or not loading so the type taken here is Boolean , and initialized it to false
    // Second possibility is , the app might through error
    var error: String? = null,
    // Third possibility is , the app might have data which obviously it will show too...
    var data: ApiResponse? = null

)