package com.example.prototipostudy_1.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40 )
val ExtraColors = listOf(
    Cores("Orange",Color(0xFFE56112)),
    Cores("Yellow",Color(0xFFF3D068)),
    Cores("Green",Color(0xFF316D09)),
    Cores("Blue ciano",Color(0xFF3EEFEC)),
    Cores("Purple",Color(0xFF963EEF)),
    Cores("Blue",Color(0xFF272AF5)),
    Cores("Dark green",Color(0xFF1B4403)),
    Cores("Dark red",Color(0xFF9C0707)),
    Cores("light green",Color(0xFF95F863)),
)
/*

val Red_power = Color(0xFFE51212)

val Yellow_ligth= Color(0xFFF3D068)

val green_power = Color(0xFF316D09)

val Blue_ciano = Color(0xFF3EEFEC)

val Purple_ligth = Color(0xFF963EEF)

val Pink_ligth = Color(0xFFF368D5)
*/

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
@Composable
fun PrototipoStudy_1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}