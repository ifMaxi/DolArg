package com.maxidev.dolarg.data.repository

import com.maxidev.dolarg.domain.model.Dollars

interface DollarsRepository {

    suspend fun dollars(): List<Dollars>
}