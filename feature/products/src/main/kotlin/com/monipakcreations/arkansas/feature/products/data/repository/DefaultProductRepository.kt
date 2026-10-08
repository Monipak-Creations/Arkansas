package com.monipakcreations.arkansas.feature.products.data.repository

import com.monipakcreations.arkansas.core.database.dao.ProductDao
import com.monipakcreations.arkansas.feature.products.data.mapper.asEntity
import com.monipakcreations.arkansas.feature.products.data.mapper.asExternalModel
import com.monipakcreations.arkansas.feature.products.data.remote.ProductsRemoteDataSource
import com.monipakcreations.arkansas.feature.products.domain.model.Product
import com.monipakcreations.arkansas.feature.products.domain.repository.ProductRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** Single source of truth is Room; the network only refreshes it. */
internal class DefaultProductRepository @Inject constructor(
    private val remote: ProductsRemoteDataSource,
    private val dao: ProductDao,
) : ProductRepository {

    override fun observeProducts(): Flow<List<Product>> =
        dao.observeAll().map { list -> list.map { it.asExternalModel() } }

    override suspend fun refreshProducts(): Result<Unit> =
        try {
            dao.upsertAll(remote.getProducts().products.map { it.asEntity() })
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
}
