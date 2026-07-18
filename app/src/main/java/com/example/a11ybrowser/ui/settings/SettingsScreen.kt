package com.example.a11ybrowser.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a11ybrowser.ui.browser.BrowserViewModel
import com.example.a11ybrowser.ui.components.FeatureCard
import com.example.a11ybrowser.ui.components.ToggleFeatureCard
import com.example.a11ybrowser.ui.profiles.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    browserViewModel: BrowserViewModel,
    profileViewModel: ProfileViewModel,
    onBackClick: () -> Unit,
    onProfilesClick: () -> Unit
) {
    val profile = browserViewModel.currentProfile

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Accessibility Settings") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                actions = {
                    TextButton(onClick = onProfilesClick) {
                        Text("Profiles")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // CONTENT Section
            Text(
                "CONTENT",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            FeatureCard(
                title = "Text Size",
                currentValue = (profile.fontSize * 2).toInt(),
                maxValue = 4,
                onValueChange = { browserViewModel.updateFontSize(it / 2f) }
            )

            FeatureCard(
                title = "Line Height",
                currentValue = (profile.lineHeight * 2).toInt(),
                maxValue = 4,
                onValueChange = { browserViewModel.updateLineHeight(it / 2f) }
            )

            FeatureCard(
                title = "Letter Spacing",
                currentValue = (profile.letterSpacing * 4).toInt(),
                maxValue = 3,
                onValueChange = { browserViewModel.updateLetterSpacing(it / 4f) }
            )

            ToggleFeatureCard(
                title = "Dyslexic Font",
                checked = profile.dyslexicFont,
                onCheckedChange = { browserViewModel.toggleDyslexicFont(it) }
            )

            ToggleFeatureCard(
                title = "Highlight Links",
                checked = profile.highlightLink,
                onCheckedChange = { browserViewModel.toggleHighlightLinks(it) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // COLORS Section
            Text(
                "COLORS",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            FeatureCard(
                title = "Brightness",
                currentValue = profile.brightness + 2,
                maxValue = 4,
                onValueChange = { browserViewModel.updateBrightness(it - 2) }
            )

            FeatureCard(
                title = "Contrast",
                currentValue = profile.contrast,
                maxValue = 4,
                onValueChange = { browserViewModel.updateContrast(it) }
            )

            FeatureCard(
                title = "Saturation",
                currentValue = profile.saturation,
                maxValue = 3,
                onValueChange = { browserViewModel.updateSaturation(it) }
            )

            ToggleFeatureCard(
                title = "Grayscale",
                checked = profile.grayscale,
                onCheckedChange = { browserViewModel.toggleGrayscale(it) }
            )

            ToggleFeatureCard(
                title = "Invert Colors",
                checked = profile.invertColors,
                onCheckedChange = { browserViewModel.toggleInvertColors(it) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ORIENTATION Section
            Text(
                "ORIENTATION",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ToggleFeatureCard(
                title = "Hide Images",
                checked = profile.hideImage,
                onCheckedChange = { browserViewModel.toggleHideImages(it) }
            )

            ToggleFeatureCard(
                title = "Reading Mask",
                checked = profile.readingMask,
                onCheckedChange = { browserViewModel.toggleReadingMask(it) }
            )

            ToggleFeatureCard(
                title = "Mute Audio",
                checked = profile.muteAudio,
                onCheckedChange = { browserViewModel.toggleMuteAudio(it) }
            )

            ToggleFeatureCard(
                title = "Stop Animations",
                checked = profile.stopAnimations,
                onCheckedChange = { browserViewModel.toggleStopAnimations(it) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // AUDIO Section
            Text(
                "AUDIO",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ToggleFeatureCard(
                title = "Text-to-Speech",
                checked = profile.ttsEnabled,
                onCheckedChange = { browserViewModel.toggleTTS(it) }
            )

            FeatureCard(
                title = "TTS Speed",
                currentValue = (profile.ttsSpeed * 2).toInt(),
                maxValue = 4,
                onValueChange = { browserViewModel.updateTTSSpeed(it / 2f) }
            )

            FeatureCard(
                title = "TTS Pitch",
                currentValue = (profile.ttsPitch * 2).toInt(),
                maxValue = 3,
                onValueChange = { browserViewModel.updateTTSPitch(it / 2f) }
            )

            ToggleFeatureCard(
                title = "Auto-Read Links",
                checked = profile.autoReadLinks,
                onCheckedChange = { browserViewModel.toggleAutoReadLinks(it) }
            )
        }
    }
}