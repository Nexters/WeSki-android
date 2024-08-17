package com.dieski.weski.presentation.detail.congestion.model

import androidx.compose.ui.graphics.Color
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */
enum class CongestionLevel(
	val presentColor: Color,
	val pastColor: Color
) {
	LOW(
		presentColor = WeskiColor.Main04,
		pastColor = WeskiColor.Gray30
	),
	MID(
		presentColor = WeskiColor.Main03,
		pastColor = WeskiColor.Gray40
	),
	HIGH(
		presentColor = WeskiColor.Main02,
		pastColor = WeskiColor.Gray50
	),
	MAX(
		presentColor = WeskiColor.Main01,
		pastColor = WeskiColor.Gray60
	)
}