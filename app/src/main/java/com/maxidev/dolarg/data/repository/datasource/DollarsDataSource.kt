package com.maxidev.dolarg.data.repository.datasource

import com.maxidev.dolarg.data.remote.ApiService
import com.maxidev.dolarg.domain.mapper.toExternalModel
import com.maxidev.dolarg.domain.model.Dollars
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DollarsDataSource @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchDollars(): List<Dollars> =
        withContext(ioDispatcher) {
            apiService.getDollars().map {
                it.toExternalModel()
            }
        }
}