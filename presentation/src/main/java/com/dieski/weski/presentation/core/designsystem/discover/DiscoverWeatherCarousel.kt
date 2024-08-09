package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
@Composable
fun DiscoverWeatherCarousel(
	modifier: Modifier = Modifier
) {
	val testData = listOf(false, true, false, false, false)
	LazyRow(
		modifier = modifier.fillMaxWidth()
	) {
		itemsIndexed(testData) { index, isToday ->
			key(index) {
				DiscoverWeather(isToday = isToday)
			}
		}
	}
}

@ThemePreviews
@DevicePreviews
@Composable
private fun DiscoverWeatherCarouselPreview() {
	DiscoverWeatherCarousel()
}