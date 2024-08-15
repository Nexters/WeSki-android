package com.dieski.weski.presentation.detail

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.weski.presentation.core.designsystem.component.LoadingIndicator
import com.dieski.weski.presentation.core.designsystem.discover.DiscoverCard
import com.dieski.weski.presentation.core.designsystem.header.WeskiHeader
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.model.WeatherType
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.detail.component.DetailSnowQualitySurvey
import com.dieski.weski.presentation.detail.component.DetailViewPagerWithTab

/**
 *
 * @author   JGeun
 * @created  2024/08/06
 */
@Composable
internal fun DetailRouter(
	skiResortName: String,
	padding: PaddingValues,
	onNavigateUp: () -> Unit,
	onShowSnackBar: (message: String, action: String?) -> Unit,
	viewModel: DetailViewModel = hiltViewModel()
) {

	val state by viewModel.uiState.collectAsStateWithLifecycle()

	LaunchedEffect(Unit) {
		viewModel.handleEvent(DetailContract.Event.FetchingSkiResortData(skiResortName))
	}

	DetailScreen(
		state = state,
		onNavigateUp = onNavigateUp ,
		onShowSnackBar = onShowSnackBar,
		modifier = Modifier
			.fillMaxSize()
			.padding(padding)
	)
}

@Composable
internal fun DetailScreen(
	modifier: Modifier = Modifier,
	state: DetailContract.State = DetailContract.State.Loading,
	onNavigateUp: () ->Unit = {},
	onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> }
) {
	val scrollState = rememberScrollState()
	var cardOffset by remember { mutableStateOf(0f) }
	val changeHeader by remember { derivedStateOf { scrollState.value > cardOffset }}

	Column(
		modifier = modifier
			.background(WeskiColor.Main05)
	) {
		WeskiHeader(
			bgColor = if (changeHeader) WeskiColor.White else Color.Transparent,
			showBackButton = true,
			showShareButton = true,
			onClickBackButton = { onNavigateUp() }
		)

		Box(
			modifier = Modifier.fillMaxSize()
		) {
			if (state is DetailContract.State.Success) {
				DetailContent(
					measureWeatherCardPosition = {
						cardOffset = it
					},
					onShowSnackBar = onShowSnackBar
				)
			} else {
				LoadingIndicator()
			}
		}
	}
}

@Composable
internal fun DetailContent(
	measureWeatherCardPosition: (Float) -> Unit,
	modifier: Modifier = Modifier,
	onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> }
) {
	val density = LocalDensity.current
	Column(
		modifier = modifier
			.fillMaxSize()
	) {
		Box(
			modifier = Modifier.padding(vertical = 28.dp, horizontal = 21.dp)
		) {
			DiscoverCard(
				modifier = Modifier.onGloballyPositioned {
					measureWeatherCardPosition(  it.positionInRoot().y )
				},
				resortName = "용평스키장 모나",
				operatingSlopeCount = 5,
				currentTemperature = 7,
				weatherType = WeatherType.SNOW,
				weatherDescription = "흐리고 눈"
			)
		}
		
		DetailViewPagerWithTab(
			onShowSnackBar = onShowSnackBar
		)
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun DetailScreenPreview() {
	DetailScreen()
}