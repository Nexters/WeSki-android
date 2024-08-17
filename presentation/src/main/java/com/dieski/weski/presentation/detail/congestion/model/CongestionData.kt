package com.dieski.weski.presentation.detail.congestion.model

import androidx.compose.runtime.Immutable

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */
@Immutable
data class CongestionData(
	val time: Int,
	val level: CongestionLevel,
	val isPast: Boolean
)