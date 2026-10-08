package com.monipakcreations.arkansas.feature.products.presentation.list

import com.monipakcreations.arkansas.feature.products.domain.model.Product

data class ProductsUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)
