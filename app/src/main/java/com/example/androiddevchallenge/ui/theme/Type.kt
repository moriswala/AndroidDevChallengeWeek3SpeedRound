package com.example.androiddevchallenge.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

private val kulimparkFonts = FontFamily(
    Font(R.font.kulimpark_regular),
    Font(R.font.kulimpark_semibold, FontWeight.W500),
    Font(R.font.kulimpark_bold, FontWeight.Bold)
)

private val latoFonts = FontFamily(
    Font(R.font.lato_regular),
    Font(R.font.lato_bold, FontWeight.Bold)
)

val typography = typographyFromDefaults(
    h1 = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        letterSpacing = 1.15.sp
    ),
    h2 = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = 1.15.sp,

    ),
    h3 = TextStyle(
        fontFamily = latoFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp
    ),
    h5 = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.Bold
    ),
    h6 = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.W500,
        lineHeight = 28.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.W500,
        lineHeight = 22.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.W500
    ),
    body1 = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp
    ),
    body2 = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp
    ),
    button = TextStyle(
        fontFamily = latoFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 1.15.sp
    ),
    caption = TextStyle(
        fontFamily = kulimparkFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 1.15.sp
    ),
    overline = TextStyle(
        letterSpacing = 0.08.em
    )
)

fun typographyFromDefaults(
    h1: TextStyle?,
    h2: TextStyle?,
    h3: TextStyle?,
    h4: TextStyle?,
    h5: TextStyle?,
    h6: TextStyle?,
    subtitle1: TextStyle?,
    subtitle2: TextStyle?,
    body1: TextStyle?,
    body2: TextStyle?,
    button: TextStyle?,
    caption: TextStyle?,
    overline: TextStyle?
): Typography {
    val defaults = Typography()
    return Typography(
        h1 = defaults.h1.merge(h1),
        h2 = defaults.h2.merge(h2),
        h3 = defaults.h3.merge(h3),
        h4 = defaults.h4.merge(h4),
        h5 = defaults.h5.merge(h5),
        h6 = defaults.h6.merge(h6),
        subtitle1 = defaults.subtitle1.merge(subtitle1),
        subtitle2 = defaults.subtitle2.merge(subtitle2),
        body1 = defaults.body1.merge(body1),
        body2 = defaults.body2.merge(body2),
        button = defaults.button.merge(button),
        caption = defaults.caption.merge(caption),
        overline = defaults.overline.merge(overline)
    )
}
