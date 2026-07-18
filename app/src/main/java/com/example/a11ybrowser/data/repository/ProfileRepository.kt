package com.example.a11ybrowser.data.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import com.example.a11ybrowser.data.model.A11yProfile
import kotlinx.serialization.encodeToString

class ProfileRepository(context: Context) {

    private val prefs = context.getSharedPreferences("a11y_profiles", MODE_PRIVATE)

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    fun getAllProfiles(): List<A11yProfile> {
        val profilesJson = prefs.getString("all_profiles", null)
            ?: return listOf(A11yProfile.default())

        return try {
            json.decodeFromString(profilesJson)
        } catch(e: Exception) {
            listOf(A11yProfile.default())
        }
    }

    fun saveProfile(profile: A11yProfile) {
        val allProfiles = getAllProfiles().toMutableList()

        val existingIndex = allProfiles.indexOfFirst { it.id == profile.id }

        if(existingIndex != -1){
            allProfiles[existingIndex] = profile
        } else {
            allProfiles.add(profile)
        }

        val profilesJson = json.encodeToString(allProfiles)
        prefs.edit()
            .putString("all_profiles", profilesJson)
            .apply()
    }

    fun deleteProfile(profileId: String) {
        val allProfiles = getAllProfiles()
            .filter { it.id != profileId }

        val profilesJson = json.encodeToString(allProfiles)
        prefs.edit()
            .putString("all_profiles", profilesJson)
            .apply()
    }

    fun getActiveProfile(): A11yProfile {
        val activeId = prefs.getString("active_profile_id", "default")
        val allProfiles = getAllProfiles()

        return allProfiles.find { it.id == activeId } ?: A11yProfile.default()
    }

    fun setActiveProfile(profileId: String) {
        prefs.edit()
            .putString("active_profile_id", profileId)
            .apply()
    }
}

