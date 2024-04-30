package com.maxidev.dolarg.di

import com.maxidev.dolarg.data.repository.DollarsRepository
import com.maxidev.dolarg.data.repository.impl.DollarsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(
        repositoryImpl: DollarsRepositoryImpl
    ): DollarsRepository
}