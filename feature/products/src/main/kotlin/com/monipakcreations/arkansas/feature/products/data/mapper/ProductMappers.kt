package com.monipakcreations.arkansas.feature.products.data.mapper

import com.monipakcreations.arkansas.core.database.model.ProductEntity
import com.monipakcreations.arkansas.feature.products.data.remote.dto.ProductDto
import com.monipakcreations.arkansas.feature.products.domain.model.Product

internal fun ProductDto.asEntity() = ProductEntity(
    id = id,
    title = title,
    description = description,
    category = category,
    brand = brand.orEmpty(),
    price = price,
    rating = rating,
    thumbnailUrl = thumbnail,
)

internal fun ProductEntity.asExternalModel() = Product(
    id = id,
    title = title,
    description = description,
    category = category,
    brand = brand,
    price = price,
    rating = rating,
    thumbnailUrl = thumbnailUrl,
)
