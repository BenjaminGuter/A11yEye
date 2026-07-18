package com.example.a11ybrowser.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a11ybrowser.ui.browser.BrowserScreen
import com.example.a11ybrowser.ui.browser.BrowserViewModel
import com.example.a11ybrowser.ui.profiles.ProfileViewModel
import com.example.a11ybrowser.ui.profiles.ProfilesScreen
import com.example.a11ybrowser.ui.settings.SettingsScreen

@Composable
fun AppNavigation(
    browserViewModel: BrowserViewModel,
    profileViewModel: ProfileViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "browser",
        modifier = modifier
    ) {
        composable("browser") {
            BrowserScreen(
                viewModel = browserViewModel,
                profileViewModel = profileViewModel,
                onSettingsClick = { navController.navigate("settings") }
            )
        }

        composable("settings") {
            SettingsScreen(
                browserViewModel = browserViewModel,
                profileViewModel = profileViewModel,
                onBackClick = { navController.popBackStack() },
                onProfilesClick = { navController.navigate("profiles") }
            )
        }

        composable("profiles") {
            ProfilesScreen(
                viewModel = profileViewModel,
                browserViewModel = browserViewModel,  // ← NEU!
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}