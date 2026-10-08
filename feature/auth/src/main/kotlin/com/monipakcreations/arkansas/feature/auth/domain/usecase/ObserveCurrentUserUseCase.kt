package com.monipakcreations.arkansas.feature.auth.domain.usecase

import com.monipakcreations.arkansas.feature.auth.domain.repository.AuthRepository
import com.monipakcreations.arkansas.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Flow<User?> = authRepository.currentUser
}
