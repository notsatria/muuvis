package com.notsatria.muuvis.launcher.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.notsatria.core.data.repository.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val userPreferenceRepository: UserPreferenceRepository) :
    ViewModel() {

    fun setUserIsLoggedIn(isLoggedIn: Boolean) {
        viewModelScope.launch {
            userPreferenceRepository.setUserLoginStatus(isLoggedIn)
        }
    }
}