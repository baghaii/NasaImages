package com.example.nasaimages.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaimages.networking.NasaFetcherService
import com.example.nasaimages.networking.NasaImagesWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel() {
    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()
    val service = NasaFetcherService.retrofit
    val TAG = this::class.simpleName
    val coroutineExceptionHandler: CoroutineExceptionHandler by lazy {
        CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
    }
    init {
        try {
            getImages()
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "Whoops")
        }
    }

    /*
        In a real project, you would build out a database and put your information in that database
        and only reach out to the network if your data was not in the database already.
        TODO - Build some caching
       */
    private fun getImages() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        _state.value = MainUiState(true)
        delay(2000) // Wait for two seconds so you can see the loading screen. Ooh. Loading
        _state.value = MainUiState(data = service.getImageData(searchTerm = "mars"))
    }

    data class MainUiState(
        val isLoading: Boolean = false,
        val data: NasaImagesWrapper? = null
    )
}