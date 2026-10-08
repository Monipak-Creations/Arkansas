package com.monipakcreations.arkansas.feature.auth.data.repository

import com.monipakcreations.arkansas.feature.auth.data.mapper.asEntity
import com.monipakcreations.arkansas.feature.auth.data.mapper.asExternalModel
import com.monipakcreations.arkansas.core.database.dao.UserDao
import com.monipakcreations.arkansas.core.datastore.SessionDataSource
import com.monipakcreations.arkansas.core.datastore.UserSession
import com.monipakcreations.arkansas.feature.auth.domain.repository.AuthRepository
import com.monipakcreations.arkansas.feature.auth.domain.model.User
import com.monipakcreations.arkansas.feature.auth.data.remote.AuthRemoteDataSource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Network (Ktor) -> cache (Room) + session (DataStore). The UI always reads from Room.
 */
internal class DefaultAuthRepository @Inject constructor(
    private val network: AuthRemoteDataSource,
    private val userDao: UserDao,
    private val sessionDataSource: SessionDataSource,
) : AuthRepository {

    override val isLoggedIn: Flow<Boolean> = sessionDataSource.session.map { it != null }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val currentUser: Flow<User?> = sessionDataSource.session.flatMapLatest { session ->
        if (session == null) {
            flowOf(null)
        } else {
            userDao.observeById(session.userId).map { it?.asExternalModel() }
        }
    }

    override suspend fun login(username: String, password: String): Result<User> =
        try {
            val response = network.login(username, password)
            val entity = response.asEntity()
            userDao.upsert(entity)
            sessionDataSource.save(
                UserSession(
                    userId = response.id,
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken,
                ),
            )
            Result.success(entity.asExternalModel())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun logout() {
        sessionDataSource.clear()
        userDao.deleteAll()
    }
}
