package com.example.a11ybrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.a11ybrowser.data.repository.ProfileRepository
import com.example.a11ybrowser.navigation.AppNavigation
import com.example.a11ybrowser.ui.browser.BrowserViewModel
import com.example.a11ybrowser.ui.profiles.ProfileViewModel
import com.example.a11ybrowser.ui.theme.A11yBrowserTheme

class MainActivity : ComponentActivity() {

    private lateinit var profileRepository: ProfileRepository
    private lateinit var browserViewModel: BrowserViewModel
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Repository
        profileRepository = ProfileRepository(applicationContext)

        // Initialize ViewModels - REIHENFOLGE WICHTIG!
        profileViewModel = ProfileViewModel(profileRepository)
        browserViewModel = BrowserViewModel(
            context = applicationContext,
            onProfileChanged = { profile ->
                // Speichere Änderungen automatisch!
                profileViewModel.saveProfile(profile)
            }
        )

        // Synchronisiere beim App-Start!
        browserViewModel.loadProfile(profileViewModel.activeProfile)

        setContent {
            A11yBrowserTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        browserViewModel = browserViewModel,
                        profileViewModel = profileViewModel
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cleanup wenn nötig
    }
}