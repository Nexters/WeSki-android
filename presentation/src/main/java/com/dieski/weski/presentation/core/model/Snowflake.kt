package com.dieski.weski.presentation.core.model

import androidx.compose.ui.geometry.Offset

/**
 *
 * @author   JGeun
 * @created  2024/09/17
 */
data class Snowflake(
	var offset: Offset,
	val angle: Float,
	val createTime: Long
)