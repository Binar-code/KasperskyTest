package com.example.kasperskytest.ui.theme

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
    surface = DarkGray,
    background = DarkGray,
    primary = LightGray,
    secondary = LightGray61,
    onPrimary = Color.White,
    onSecondary = LightGray79,
)

private val LightColorScheme = lightColorScheme(
    surface = Cream,
    background = Cream,
    primary = Color.White,
    secondary = SecondaryCream,
    onPrimary = Color.Black,
    onSecondary = LightGray66,
)

@Composable
fun KasperskyTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
