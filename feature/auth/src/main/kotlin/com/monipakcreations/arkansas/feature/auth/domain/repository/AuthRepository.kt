package com.monipakcreations.arkansas.feature.auth.domain.repository

import com.monipakcreations.arkansas.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val isLoggedIn: Flow<Boolean>
    val currentUser: Flow<User?>
    suspend fun login(username: String, password: String): Result<User>
    suspend fun logout()
}
