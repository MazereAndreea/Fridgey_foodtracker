package com.example.fridgey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fridgey.models.AppSettings
import com.example.fridgey.ui.theme.*

class AppSettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel: AppSettings = viewModel()
            val isDarkTheme = settingsViewModel.isDarkTheme.collectAsState().value

            FridgeyTheme(darkTheme = isDarkTheme) {
                SettingsScreen(settingsViewModel = settingsViewModel)
            }
        }
    }
}