package dev.campi.apipunksolution.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val PrimaryLightColor = Color(0xff66d8ff)
val PrimaryColor = Color(0xff1da6cc)
val PrimaryDarkColor = Color(0xff00779b)

val ItemBeerHeader = PrimaryDarkColor.copy(alpha = STANDARD_ALPHA)

val TextBlackAplhaMedium = Color.Black.copy(alpha = STANDARD_ALPHA)

val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFD8D8D8)

val Colors.topAppBarContentColor: Color
    get() = if (isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor: Color
    get() = if (isLight) PrimaryColor else Color.Black