package com.maxidev.dolarg.di

import com.maxidev.dolarg.data.remote.ApiService
import com.maxidev.dolarg.data.repository.datasource.DollarsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesDataSource(apiService: ApiService): DollarsDataSource =
        DollarsDataSource(apiService)
}