package com.example.fridgey.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.flow.StateFlow

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF9d2b22),
    secondary = Color(0xFF03DAC5),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF9d2b22),
    secondary = Color(0xFF03DAC5),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun FridgeyTheme(
    darkTheme: StateFlow<Boolean>,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val isDarkTheme = darkTheme.collectAsState()
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme.value) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDarkTheme.value -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme.value
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)