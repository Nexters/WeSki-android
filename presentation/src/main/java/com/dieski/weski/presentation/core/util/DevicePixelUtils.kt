package com.dieski.weski.presentation.core.util

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// dp(Dp) → px(Float)
@Composable
internal fun Dp.dpToPx(): Float = this.value * LocalDensity.current.density

internal fun Float.dpToPx(context: Context): Float {
	val metrics = context.resources.displayMetrics
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics)
}

// dp(Dp) → sp(TextUnit)
@Composable
internal fun Dp.dpToSp(): TextUnit = (this.value * LocalDensity.current.density / LocalDensity.current.fontScale).sp

// px(Float) → dp(Dp)
@Composable
internal fun Float.pxToDp(): Dp = (this / LocalDensity.current.density).dp

internal fun Float.pxToDp(context: Context): Float {
	val metrics = context.resources.displayMetrics
	return this / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

// px(Float) → sp(TextUnit)
@Composable
internal fun Float.pxToSp(): TextUnit = (this / LocalDensity.current.fontScale).sp

// sp(TextUnit) → dp(Dp)
@Composable
internal fun TextUnit.spToDp(): Dp = (this.value * LocalDensity.current.fontScale / LocalDensity.current.density).dp

// sp(TextUnit) → px(Float)
@Composable
internal fun TextUnit.spToPx(): Float = this.value * LocalDensity.current.fontScale