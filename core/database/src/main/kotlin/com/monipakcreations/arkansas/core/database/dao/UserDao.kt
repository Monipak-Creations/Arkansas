package com.monipakcreations.arkansas.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.monipakcreations.arkansas.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsert(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :id")
    fun observeById(id: Int): Flow<UserEntity?>

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}
