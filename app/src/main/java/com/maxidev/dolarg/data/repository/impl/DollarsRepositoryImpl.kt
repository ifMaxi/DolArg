package com.maxidev.dolarg.data.repository.impl

import com.maxidev.dolarg.data.repository.DollarsRepository
import com.maxidev.dolarg.data.repository.datasource.DollarsDataSource
import com.maxidev.dolarg.domain.model.Dollars
import javax.inject.Inject

class DollarsRepositoryImpl @Inject constructor(
    private val dataSource: DollarsDataSource
): DollarsRepository {

    override suspend fun dollars(): List<Dollars> =
        dataSource.fetchDollars()
}