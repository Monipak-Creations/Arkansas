package com.monipakcreations.arkansas.feature.auth.domain.usecase

import com.monipakcreations.arkansas.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIsLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Flow<Boolean> = authRepository.isLoggedIn
}
