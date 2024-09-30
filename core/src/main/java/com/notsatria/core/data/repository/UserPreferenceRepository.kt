package com.notsatria.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.notsatria.core.domain.repository.IUserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferenceRepository @Inject constructor(private val userPreference: DataStore<Preferences>) :
    IUserPreferenceRepository {
    override suspend fun setUserLoginStatus(isLoggedIn: Boolean) {
        userPreference.edit { pref ->
            pref[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

    override fun getUserLoginStatus(): Flow<Boolean> {
        return userPreference.data.map { pref ->
            pref[IS_LOGGED_IN_KEY] ?: false
        }
    }

    companion object {
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }
}