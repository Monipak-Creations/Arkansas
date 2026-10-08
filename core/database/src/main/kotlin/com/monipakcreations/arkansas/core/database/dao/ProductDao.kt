package com.monipakcreations.arkansas.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.monipakcreations.arkansas.core.database.model.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products ORDER BY id")
    fun observeAll(): Flow<List<ProductEntity>>

    @Upsert
    suspend fun upsertAll(products: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun deleteAll()
}
