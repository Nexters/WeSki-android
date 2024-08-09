package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
@Composable
fun DiscoverCardWithWeatherCarousel(
	modifier: Modifier = Modifier,
	bgColor: Color = WeskiColor.Gray10,
	cornerDp: Dp = 15.dp
) {
	Column(
		modifier = modifier
			.background(color = bgColor, shape = RoundedCornerShape(cornerDp))
	) {
		DiscoverCard(
			bgColor = bgColor,
			cornerDp = cornerDp
		)
		
		Spacer(modifier = Modifier.height(23.dp))

		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(1.dp)
				.padding(horizontal = 24.dp)
				.background(WeskiColor.Gray80)
		)

		Spacer(modifier = Modifier.height(14.dp))

		DiscoverWeatherCarousel(
			modifier = Modifier.padding(start = 14.dp)
		)
	}

}

@ThemePreviews
@DevicePreviews
@Composable
private fun DiscoverCardWithWeatherCarouselPreview() {
	DiscoverCardWithWeatherCarousel()
}