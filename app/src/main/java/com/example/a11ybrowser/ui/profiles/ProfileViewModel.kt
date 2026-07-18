package com.example.a11ybrowser.ui.profiles

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.a11ybrowser.data.repository.ProfileRepository
import com.example.a11ybrowser.data.model.A11yProfile

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    var profiles by mutableStateOf<List<A11yProfile>>(emptyList())
    var activeProfile by mutableStateOf(A11yProfile.default())

    init {
        loadProfiles()
    }

    private fun loadProfiles() {
        profiles = repository.getAllProfiles()
        activeProfile = repository.getActiveProfile()
    }

    fun saveProfile(profile: A11yProfile) {
        repository.saveProfile(profile)
        loadProfiles()
    }

    fun deleteProfile(profileId: String) {
        repository.deleteProfile(profileId)
        loadProfiles()
    }

    fun setActive(profileId: String) {
        repository.setActiveProfile(profileId)
        loadProfiles()
    }
}