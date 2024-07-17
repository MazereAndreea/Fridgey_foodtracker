package com.example.fridgey.database

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("theme_pref")

object StoreTheme {
    val isDarkTheme = booleanPreferencesKey("is_dark_theme")
}

suspend fun setTheme(context: Context, isDarkTheme: Boolean) {
    context.dataStore.edit { preferences ->
        preferences[StoreTheme.isDarkTheme] = isDarkTheme
    }
}

fun getTheme(context: Context): Flow<Boolean> {
    return context.dataStore.data.map { preferences ->
        preferences[StoreTheme.isDarkTheme] ?: false
    }
}