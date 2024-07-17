package com.example.fridgey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import composables.AppNavHost

import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fridgey.models.view.AppSettings
import com.example.fridgey.ui.theme.FridgeyTheme
import composables.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsViewModel: AppSettings = viewModel()

            FridgeyTheme(darkTheme = settingsViewModel.isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val settingsViewModel: AppSettings = viewModel()
    FridgeyTheme (darkTheme = settingsViewModel.isDarkTheme) {
        MainScreen(rememberNavController())
    }
}