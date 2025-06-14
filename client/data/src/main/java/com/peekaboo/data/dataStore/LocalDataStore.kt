package com.peekaboo.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import timber.log.Timber

private val Context.dataStore by preferencesDataStore(name = "baebae_prefs")

class LocalDataStore(context: Context) {
    private val dataStore = context.dataStore

    val accessToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }

    val refreshToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[REFRESH_TOKEN_KEY]
        }

    suspend fun saveAccessToken(token: String): Boolean {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
            Timber.d("[datastore] access token: $token")
        }

        val savedToken = dataStore.data.map { it[ACCESS_TOKEN_KEY] }.firstOrNull()
        return savedToken == token
    }

    suspend fun saveRefreshToken(token: String): Boolean {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN_KEY] = token
            Timber.d("[datastore] refresh token: $token")
        }

        val savedToken = dataStore.data.map { it[REFRESH_TOKEN_KEY] }.firstOrNull()
        return savedToken == token
    }

    companion object {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }
}