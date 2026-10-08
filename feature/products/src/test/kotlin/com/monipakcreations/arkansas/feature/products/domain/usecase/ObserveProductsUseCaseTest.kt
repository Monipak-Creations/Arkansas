package com.monipakcreations.arkansas.feature.products.domain.usecase

import com.monipakcreations.arkansas.feature.products.domain.model.Product
import com.monipakcreations.arkansas.feature.products.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ObserveProductsUseCaseTest {

    private val product = Product(1, "Mascara", "d", "beauty", "Essence", 9.99, 4.9, "")

    private val repository = object : ProductRepository {
        override fun observeProducts(): Flow<List<Product>> = flowOf(listOf(product))
        override suspend fun refreshProducts(): Result<Unit> = Result.success(Unit)
    }

    @Test
    fun invoke_emitsRepositoryProducts() = runTest {
        assertEquals(listOf(product), ObserveProductsUseCase(repository)().first())
    }
}
