package com.monipakcreations.arkansas.feature.products.data.remote

import com.monipakcreations.arkansas.core.network.apiCall
import com.monipakcreations.arkansas.feature.products.data.remote.dto.ProductsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/** `GET https://dummyjson.com/products` */
class ProductsRemoteDataSource @Inject constructor(
    private val client: HttpClient,
) {
    suspend fun getProducts(limit: Int = 30, skip: Int = 0): ProductsResponseDto = apiCall {
        client.get("products") {
            parameter("limit", limit)
            parameter("skip", skip)
        }.body<ProductsResponseDto>()
    }
}
