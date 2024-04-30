package com.maxidev.dolarg.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.dolarg.data.repository.DollarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DollarsViewModel @Inject constructor(
    private val repository: DollarsRepository
): ViewModel() {

    private val _remoteStatus: MutableStateFlow<RemoteStatus> =
        MutableStateFlow(RemoteStatus.Loading)
    val remoteStatus = _remoteStatus.asStateFlow()

    init {
        dollarStream()
    }

    private fun dollarStream() {
        viewModelScope.launch {
            _remoteStatus.value = RemoteStatus.Loading
            delay(2000L)
            _remoteStatus.value = try {
                RemoteStatus.Success(onSuccess = repository.dollars())
            } catch (e: IOException) {
                RemoteStatus.Error(onError = e)
            } catch (e: HttpException) {
                RemoteStatus.Error(onError = e)
            }
        }
    }
}