package com.monipakcreations.arkansas.feature.products.data.remote.dto

import kotlinx.serialization.Serializable

/** Response of `GET https://dummyjson.com/products`. */
@Serializable
data class ProductsResponseDto(
    val products: List<ProductDto> = emptyList(),
    val total: Int = 0,
    val skip: Int = 0,
    val limit: Int = 0,
)

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String = "",
    val category: String = "",
    val brand: String? = null,
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val thumbnail: String = "",
)
