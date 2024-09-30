package com.notsatria.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.notsatria.core.data.preferences.dataStore
import com.notsatria.core.data.repository.UserPreferenceRepository
import com.notsatria.core.domain.repository.IUserPreferenceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    @Singleton
    abstract fun bindUserPreference(
        userPreferenceRepository: UserPreferenceRepository
    ): IUserPreferenceRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserDataStorePreference(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
            return applicationContext.dataStore
        }
    }
}