package com.example.probcal.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Cyan100,
    secondary = Color.White,
//    tertiary = Cyan100,
    background = Color(0xFF151D28),
    onBackground = Color.White,
    surface = Cyan100,
    onSurface = Color.White,

    onSurfaceVariant = Color(0xB3FFFFFF),

//    Card
    surfaceContainerHighest = Color(0xFF1F2733),
)

private val LightColorScheme = lightColorScheme(
    primary = Cyan100,
    secondary = Cyan70,
//    tertiary = Cyan100,
    background = Color(0xFFF8FAFC),
    surface = Cyan100,
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
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}