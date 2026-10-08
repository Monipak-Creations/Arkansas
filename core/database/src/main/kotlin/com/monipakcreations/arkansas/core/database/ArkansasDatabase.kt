package com.monipakcreations.arkansas.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.monipakcreations.arkansas.core.database.dao.ProductDao
import com.monipakcreations.arkansas.core.database.dao.UserDao
import com.monipakcreations.arkansas.core.database.model.ProductEntity
import com.monipakcreations.arkansas.core.database.model.UserEntity

@Database(
    entities = [UserEntity::class, ProductEntity::class],
    version = 2,
    exportSchema = true,
)
abstract class ArkansasDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}
