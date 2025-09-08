package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
private val glassMorphismBgColor = Brush.linearGradient(listOf(Color(0xE6FFFFFF), Color(0x99FFFFFF)))

@Composable
fun DiscoverCard(
	resortName: String,
	status: String,
	weatherCondition: WeatherCondition,
	currentTemperature: Int,
	isBookmarked: Boolean,
	onClickBookmark: () -> Unit,
	modifier: Modifier = Modifier,
	cornerDp: Dp = 15.dp,
	mainContentPaddingValues: PaddingValues = PaddingValues(top = 34.dp, bottom = 34.dp),
	bottomContent: @Composable () -> Unit = {}
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.background(brush = glassMorphismBgColor, shape = RoundedCornerShape(cornerDp))
			.padding(mainContentPaddingValues)
	) {
		Column (
			modifier = Modifier.fillMaxWidth()
				.padding(vertical = 4.dp)
		) {
			DiscoverCardHeader(
				resortName = resortName,
				isBookmarked = isBookmarked,
				weatherCondition = weatherCondition,
				currentTemperature = currentTemperature,
				onClickBookmark = onClickBookmark
			)

			DiscoverCardStatus(
				status = status,
				weatherConditionText = weatherCondition.korean
			)
		}


		bottomContent()
	}
}

@ThemePreviews
@DevicePreviews
@Composable
private fun DiscoverCardPreview() {
	DiscoverCard(
		resortName = "용평스키장 모나",
		currentTemperature = 7,
		weatherCondition = WeatherCondition.SNOW,
		status = "운영 중",
		isBookmarked = true,
		onClickBookmark = {}
	)
}

