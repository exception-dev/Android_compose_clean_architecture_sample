package com.ex.data.di

import com.ex.data.api.ApiService
import com.ex.data.repository.BeerRepositoryImpl
import com.ex.domain.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideBeerRepository(service: ApiService): BeerRepository {
        return BeerRepositoryImpl(service)
    }

}