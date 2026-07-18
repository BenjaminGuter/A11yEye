package com.example.a11ybrowser.ui.browser

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a11ybrowser.ui.components.WebViewComposable
import com.example.a11ybrowser.ui.profiles.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowserScreen(
    viewModel: BrowserViewModel,
    profileViewModel: ProfileViewModel,
    onSettingsClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("A11y Browser") },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(Icons.Default.Settings, "Settings")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // URL Bar
            OutlinedTextField(
                value = viewModel.currentUrl,
                onValueChange = { viewModel.updateUrl(it) },
                label = { Text("URL") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // WebView
            WebViewComposable(
                url = viewModel.currentUrl,
                profile = viewModel.currentProfile  // ← NEU!
            )
        }
    }
}