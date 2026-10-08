package com.monipakcreations.arkansas.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val brand: String,
    val price: Double,
    val rating: Double,
    val thumbnailUrl: String,
)
