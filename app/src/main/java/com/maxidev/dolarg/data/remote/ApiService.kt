package com.maxidev.dolarg.data.remote

import com.maxidev.dolarg.data.remote.dto.DollarDTOItem
import com.maxidev.dolarg.utils.Constants.DOLLARS
import retrofit2.http.GET

interface ApiService {

    @GET(DOLLARS)
    suspend fun getDollars(): List<DollarDTOItem>
}