package com.example.a11ybrowser.ui.profiles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.UUID
import com.example.a11ybrowser.ui.browser.BrowserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilesScreen(
    viewModel: ProfileViewModel,
    browserViewModel: BrowserViewModel,  // ← NEU!
    onBackClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var profileName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profiles") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, "Add Profile")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(viewModel.profiles) { profile ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    onClick = {
                        viewModel.setActive(profile.id)
                        browserViewModel.loadProfile(profile)  // ← Lädt Einstellungen!
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = profile.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        if (profile.id == viewModel.activeProfile.id) {
                            Icon(Icons.Default.Check, "Active")
                        }
                    }
                }
            }
        }
    }

    // Save Profile Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Save Profile") },
            text = {
                OutlinedTextField(
                    value = profileName,
                    onValueChange = { profileName = it },
                    label = { Text("Profile Name") }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (profileName.isNotBlank()) {
                            viewModel.saveProfile(
                                browserViewModel.currentProfile.copy(  // ← RICHTIG!
                                    id = java.util.UUID.randomUUID().toString(),
                                    name = profileName
                                )
                            )
                        }
                        showDialog = false
                        profileName = ""
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}