package com.notsatria.muuvis.launcher.onboarding

import androidx.lifecycle.ViewModel
import com.notsatria.core.data.repository.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(private val userPreferenceRepository: UserPreferenceRepository) :
    ViewModel() {

    fun getUserLoginStatus() = userPreferenceRepository.getUserLoginStatus()

}