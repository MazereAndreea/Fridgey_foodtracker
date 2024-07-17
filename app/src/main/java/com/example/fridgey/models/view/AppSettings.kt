package com.example.fridgey.models.view

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.fridgey.database.*

class AppSettings(application: Application) : AndroidViewModel(application) {
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> get() = _isDarkTheme

    init {
        viewModelScope.launch {
            getTheme(application).collect { theme ->
                _isDarkTheme.value = theme
            }
        }
    }
    fun toggleTheme() {
        viewModelScope.launch {
            _isDarkTheme.value = !_isDarkTheme.value
            setTheme(getApplication(), _isDarkTheme.value)
        }
    }
}