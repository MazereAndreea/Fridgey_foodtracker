package com.example.fridgey

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fridgey.models.AppSettings
import composables.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(settingsViewModel: AppSettings = viewModel(),navController: NavHostController) {
    val isDarkTheme = settingsViewModel.isDarkTheme.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("App Settings")
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { navController.navigate(route = NavigationRoutes.main_screen.route) }
            ) {
                Text(text = "Go back to Main Screen")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Dark Theme")
                Switch(
                    checked = isDarkTheme.value,
                    onCheckedChange = { settingsViewModel.toggleTheme() }
                )
            }
        }
    }
}
