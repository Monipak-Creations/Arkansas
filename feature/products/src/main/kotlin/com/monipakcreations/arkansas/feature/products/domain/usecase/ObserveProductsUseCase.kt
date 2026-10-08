package com.monipakcreations.arkansas.feature.products.domain.usecase

import com.monipakcreations.arkansas.feature.products.domain.model.Product
import com.monipakcreations.arkansas.feature.products.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    operator fun invoke(): Flow<List<Product>> = repository.observeProducts()
}
