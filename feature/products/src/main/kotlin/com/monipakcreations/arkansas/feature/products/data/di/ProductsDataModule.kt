package com.monipakcreations.arkansas.feature.products.data.di

import com.monipakcreations.arkansas.feature.products.data.repository.DefaultProductRepository
import com.monipakcreations.arkansas.feature.products.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProductsDataModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(impl: DefaultProductRepository): ProductRepository
}
