package com.monipakcreations.arkansas.feature.auth.domain.usecase

import com.monipakcreations.arkansas.feature.auth.domain.repository.AuthRepository
import com.monipakcreations.arkansas.feature.auth.domain.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(username: String, password: String): Result<User> {
        if (username.isBlank() || password.isBlank()) {
            return Result.failure(IllegalArgumentException("Username and password are required"))
        }
        return authRepository.login(username.trim(), password)
    }
}
