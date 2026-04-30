package com.example.sabor_andino.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary            = Terracota40,
    onPrimary          = HuesoBlanco,
    primaryContainer   = Terracota80,
    onPrimaryContainer = CafeOscuro,
    secondary          = MaizDorado40,
    onSecondary        = CafeOscuro,
    secondaryContainer = MaizDorado80,
    onSecondaryContainer = CafeOscuro,
    tertiary           = VerdeAndes40,
    onTertiary         = HuesoBlanco,
    tertiaryContainer  = VerdeAndes80,
    onTertiaryContainer = CafeOscuro,
    background         = CremaCalida,
    onBackground       = CafeOscuro,
    surface            = HuesoBlanco,
    onSurface          = CafeOscuro,
)

private val DarkColors = darkColorScheme(
    primary            = Terracota80,
    onPrimary          = CafeOscuro,
    primaryContainer   = Terracota40,
    onPrimaryContainer = HuesoBlanco,
    secondary          = MaizDorado80,
    onSecondary        = CafeOscuro,
    secondaryContainer = MaizDorado40,
    onSecondaryContainer = HuesoBlanco,
    tertiary           = VerdeAndes80,
    onTertiary         = CafeOscuro,
    tertiaryContainer  = VerdeAndes40,
    onTertiaryContainer = HuesoBlanco,
    background         = Color(0xFF1A0A00),
    onBackground       = CremaCalida,
    surface            = Color(0xFF261200),
    onSurface          = CremaCalida,
)

@Composable
fun SaborAndinoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = Typography,
        content     = content
    )
}
