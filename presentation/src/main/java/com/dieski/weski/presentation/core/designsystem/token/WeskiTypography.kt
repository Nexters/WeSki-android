package com.dieski.weski.presentation.core.designsystem.token

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.ui.theme.WeskiTheme

private val pretendardStyle = FontFamily(
    Font(R.font.pretendard_regular, weight = FontWeight.Normal),
    Font(R.font.pretendard_medium, weight = FontWeight.Medium),
    Font(R.font.pretendard_semibold, weight = FontWeight.SemiBold),
    Font(R.font.pretendard_bold, weight = FontWeight.Bold)
)

private fun setDefaultLineHeight(
    alignment: LineHeightStyle.Alignment = LineHeightStyle.Alignment.Center,
    trim: LineHeightStyle.Trim = LineHeightStyle.Trim.None
) = LineHeightStyle(
    alignment = alignment,
    trim = trim
)

@Immutable
data class WeskiTypography(
    val heading1Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading1SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading1Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading15Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 1.45.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading15SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp,
        lineHeight = 1.45.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading15Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 1.45.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading2Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading2SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading2Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading3Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading3SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val heading3Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val title1Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val title1SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val title1Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val title2Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val title2SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val title2Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val title3Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val title3SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val title3Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body1Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body1SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body1Medium: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body1Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body2Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body2SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body2Medium: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body2Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body3Bold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body3SemiBold: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body3Medium: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),

    val body3Regular: TextStyle = TextStyle(
        fontFamily = pretendardStyle,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 1.6.em,
        lineHeightStyle = setDefaultLineHeight(),
        letterSpacing = 0.02.em
    ),
)

internal val LocalWeSkiTypo = staticCompositionLocalOf { WeskiTypography() }

@DevicePreviews
@ThemePreviews
@Composable
fun TypoPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically)
    ) {
        Text("heading1Bold", style = WeskiTheme.typography.heading1Bold)
        Text("heading2Bold", style = WeskiTheme.typography.heading2Bold)
        Text("heading3Bold", style = WeskiTheme.typography.heading3Bold)

        Text("heading1SemiBold", style = WeskiTheme.typography.heading1SemiBold)
        Text("heading2SemiBold", style = WeskiTheme.typography.heading2SemiBold)
        Text("heading3SemiBold", style = WeskiTheme.typography.heading3SemiBold)

        Text("heading1Regular", style = WeskiTheme.typography.heading1Regular)
        Text("heading2Regular", style = WeskiTheme.typography.heading2Regular)
        Text("heading3Regular", style = WeskiTheme.typography.heading3Regular)

        Text("body1Bold", style = WeskiTheme.typography.body1Bold)
        Text("body2Bold", style = WeskiTheme.typography.body2Bold)
        Text("body3Bold", style = WeskiTheme.typography.body3Bold)

        Text("body1SemiBold", style = WeskiTheme.typography.body1SemiBold)
        Text("body2SemiBold", style = WeskiTheme.typography.body2SemiBold)
        Text("body3SemiBold", style = WeskiTheme.typography.body3SemiBold)

        Text("body1Medium", style = WeskiTheme.typography.body1Medium)
        Text("body2Medium", style = WeskiTheme.typography.body2Medium)
        Text("body3Medium", style = WeskiTheme.typography.body3Medium)

        Text("body1Regular", style = WeskiTheme.typography.body1Regular)
        Text("body2Regular", style = WeskiTheme.typography.body2Regular)
        Text("body3Regular", style = WeskiTheme.typography.body3Regular)
    }
}
