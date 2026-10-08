package com.monipakcreations.arkansas.feature.auth.data.di

import com.monipakcreations.arkansas.feature.auth.data.repository.DefaultAuthRepository
import com.monipakcreations.arkansas.feature.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: DefaultAuthRepository): AuthRepository
}
