package com.example.fridgey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.fridgey.models.view.AppSettings
import com.example.fridgey.ui.theme.*

class AppSettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel: AppSettings = viewModel()
            val navController = rememberNavController()
            FridgeyTheme(darkTheme = settingsViewModel.isDarkTheme) {
                SettingsScreen(navController)
            }
        }
    }
}