package com.monipakcreations.arkansas.feature.products.domain.repository

import com.monipakcreations.arkansas.feature.products.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun observeProducts(): Flow<List<Product>>
    suspend fun refreshProducts(): Result<Unit>
}
