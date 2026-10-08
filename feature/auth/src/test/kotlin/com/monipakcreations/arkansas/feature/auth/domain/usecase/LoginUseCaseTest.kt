package com.monipakcreations.arkansas.feature.auth.domain.usecase

import com.monipakcreations.arkansas.feature.auth.domain.repository.AuthRepository
import com.monipakcreations.arkansas.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginUseCaseTest {

    private val repository = FakeAuthRepository()
    private val loginUseCase = LoginUseCase(repository)

    @Test
    fun blankCredentials_returnFailure_withoutCallingRepository() = runTest {
        val result = loginUseCase(" ", "")
        assertTrue(result.isFailure)
        assertEquals(0, repository.loginCalls)
    }

    @Test
    fun validCredentials_trimUsername_andReturnUser() = runTest {
        val result = loginUseCase("  emilys ", "emilyspass")
        assertEquals("emilys", result.getOrThrow().username)
        assertEquals(1, repository.loginCalls)
    }
}

private class FakeAuthRepository : AuthRepository {
    var loginCalls = 0
    private val user = MutableStateFlow<User?>(null)

    override val isLoggedIn: Flow<Boolean> = user.map { it != null }
    override val currentUser: Flow<User?> = user

    override suspend fun login(username: String, password: String): Result<User> {
        loginCalls++
        val loggedIn = User(1, username, "e@mail.com", "Emily", "Johnson", "female", "")
        user.value = loggedIn
        return Result.success(loggedIn)
    }

    override suspend fun logout() {
        user.value = null
    }
}
