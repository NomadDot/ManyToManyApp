package com.drowsynomad.presentation.core.theme

import androidx.compose.ui.graphics.Color

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Accent = Color(0xFFE6368d)

fun colorFromString(colorString: String): Color {
    return Color(android.graphics.Color.parseColor("#$colorString"))
}

fun Color.toRawColor(): String {
    val red = this.red
    val green = this.green
    val blue = this.blue

    return Integer.toHexString(android.graphics.Color.rgb(red, green, blue))
}
