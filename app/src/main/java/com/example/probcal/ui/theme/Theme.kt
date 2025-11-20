package com.example.probcal.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Yellow100,
    secondary = Color.White,
//    tertiary = Blue100,
    background = Color(0xFF151D28),
    onBackground = Color.White,
    surface = Blue100,
    onSurface = Color.White,

    onPrimary = Color.White,
    onSurfaceVariant = Color(0xB3FFFFFF),

//    Card
    surfaceContainerHighest = Color(0xFF1F2733),
)

private val LightColorScheme = lightColorScheme(
    primary = Blue100,
    secondary = Blue70,
//    tertiary = Blue100,
    background = Color(0xFFF8FAFC),
    surface = Blue100,
    onSurface = Color(0xFF0F172A),


//    Card
    surfaceContainerHighest = Color.White,

//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F)
)

@Composable
fun ProbCalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}