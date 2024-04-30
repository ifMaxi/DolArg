package com.maxidev.dolarg.presentation.home

import com.maxidev.dolarg.domain.model.Dollars

sealed interface RemoteStatus {
    data class Success(val onSuccess: List<Dollars>): RemoteStatus
    data class Error(val onError: Exception): RemoteStatus
    data object Loading: RemoteStatus
}