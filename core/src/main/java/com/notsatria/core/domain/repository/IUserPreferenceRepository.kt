package com.notsatria.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface IUserPreferenceRepository {

    suspend fun setUserLoginStatus(isLoggedIn: Boolean)

    fun getUserLoginStatus(): Flow<Boolean>
}