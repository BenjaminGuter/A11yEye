package com.example.a11ybrowser.ui.browser

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.a11ybrowser.data.model.A11yProfile

class BrowserViewModel(
    private val context: Context,
    private val onProfileChanged: (A11yProfile) -> Unit = {}  // ← NEU! Callback zum Speichern
) : ViewModel() {

    // Das aktive Profil
    var currentProfile by mutableStateOf(A11yProfile.default())
        private set

    // Noch eine wichtige Variable: Die URL!
    var currentUrl by mutableStateOf("https://wikipedia.org")
        private set

    // CONTENT Features
    fun updateFontSize(value: Float) {
        updateAndSave(currentProfile.copy(fontSize = value))
    }

    fun updateLineHeight(value: Float) {
        updateAndSave(currentProfile.copy(lineHeight = value))
    }

    fun toggleDyslexicFont(enabled: Boolean) {
        updateAndSave(currentProfile.copy(dyslexicFont = enabled))
    }

    fun updateLetterSpacing(value: Float) {
        updateAndSave(currentProfile.copy(letterSpacing = value))
    }

    fun toggleHighlightLinks(enabled: Boolean) {
        updateAndSave(currentProfile.copy(highlightLink = enabled))
    }

    // COLORS Features
    fun updateContrast(value: Int) {
        updateAndSave(currentProfile.copy(contrast = value))
    }

    fun updateBrightness(value: Int) {
        updateAndSave(currentProfile.copy(brightness = value))
    }

    fun toggleGrayscale(enabled: Boolean) {
        updateAndSave(currentProfile.copy(grayscale = enabled))
    }

    fun updateSaturation(value: Int) {
        updateAndSave(currentProfile.copy(saturation = value))
    }

    fun toggleInvertColors(enabled: Boolean) {
        updateAndSave(currentProfile.copy(invertColors = enabled))
    }

    // ORIENTATION Features
    fun toggleHideImages(enabled: Boolean) {
        updateAndSave(currentProfile.copy(hideImage = enabled))
    }

    fun toggleReadingMask(enabled: Boolean) {
        updateAndSave(currentProfile.copy(readingMask = enabled))
    }

    fun toggleMuteAudio(enabled: Boolean) {
        updateAndSave(currentProfile.copy(muteAudio = enabled))
    }

    fun toggleStopAnimations(enabled: Boolean) {
        updateAndSave(currentProfile.copy(stopAnimations = enabled))
    }

    // AUDIO Features
    fun toggleTTS(enabled: Boolean) {
        updateAndSave(currentProfile.copy(ttsEnabled = enabled))
    }

    fun updateTTSSpeed(value: Float) {
        updateAndSave(currentProfile.copy(ttsSpeed = value))
    }

    fun updateTTSPitch(value: Float) {
        updateAndSave(currentProfile.copy(ttsPitch = value))
    }

    fun toggleAutoReadLinks(enabled: Boolean) {
        updateAndSave(currentProfile.copy(autoReadLinks = enabled))
    }

    // Profil laden (von außen)
    fun loadProfile(profile: A11yProfile) {
        currentProfile = profile
        // NICHT speichern - nur laden!
    }

    // URL ändern
    fun updateUrl(url: String) {
        currentUrl = url
    }

    // HELPER: Profil updaten UND speichern
    private fun updateAndSave(newProfile: A11yProfile) {
        currentProfile = newProfile
        onProfileChanged(currentProfile)  // Callback aufrufen → speichert!
    }
}