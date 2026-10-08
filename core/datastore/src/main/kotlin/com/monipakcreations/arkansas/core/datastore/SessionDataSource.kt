package com.monipakcreations.arkansas.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    val session: Flow<UserSession?> = dataStore.data
        .map { prefs ->
            val userId = prefs[Keys.USER_ID] ?: return@map null
            val accessToken = prefs[Keys.ACCESS_TOKEN] ?: return@map null
            UserSession(
                userId = userId,
                accessToken = accessToken,
                refreshToken = prefs[Keys.REFRESH_TOKEN].orEmpty(),
            )
        }
        .distinctUntilChanged()

    suspend fun save(session: UserSession) {
        dataStore.edit { prefs ->
            prefs[Keys.USER_ID] = session.userId
            prefs[Keys.ACCESS_TOKEN] = session.accessToken
            prefs[Keys.REFRESH_TOKEN] = session.refreshToken
        }
    }

    suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    private object Keys {
        val USER_ID = intPreferencesKey("user_id")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }
}
