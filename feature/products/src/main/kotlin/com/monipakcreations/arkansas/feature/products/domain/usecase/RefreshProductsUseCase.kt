package com.monipakcreations.arkansas.feature.products.domain.usecase

import com.monipakcreations.arkansas.feature.products.domain.repository.ProductRepository
import javax.inject.Inject

class RefreshProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(): Result<Unit> = repository.refreshProducts()
}
