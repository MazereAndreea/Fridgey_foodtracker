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
import com.example.fridgey.models.view.AppSettings
import composables.NavigationRoutes
import composables.layout.FridgeyScaffold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController, settingsViewModel: AppSettings = viewModel()) {
    FridgeyScaffold(navController = navController, title = "App Settings", false) {
        paddingValues ->

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
                Text(text = if (settingsViewModel.isDarkTheme.collectAsState().value) "Dark Theme: On" else "Dark Theme: Off")
                Switch(
                    checked = settingsViewModel.isDarkTheme.collectAsState().value,
                    onCheckedChange = { settingsViewModel.toggleTheme() }
                )
            }
        }
    }
}
