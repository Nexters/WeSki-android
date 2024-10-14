package com.dieski.weski.presentation.detail

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.domain.model.SkiResortWebKey
import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.model.WeatherCondition
import com.dieski.domain.model.WebMobileData
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.discover.DiscoverCard
import com.dieski.weski.presentation.core.designsystem.header.WeskiHeader
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.collectWithLifecycle
import com.dieski.weski.presentation.detail.component.DetailViewPagerWithTab

/**
 *
 * @author   JGeun
 * @created  2024/08/06
 */
@Composable
internal fun DetailRouter(
	resortId: Long,
	padding: PaddingValues,
	onNavigateUp: () -> Unit,
	onShowSnackBar: (message: String, action: String?) -> Unit,
	viewModel: DetailViewModel = hiltViewModel()
) {
	val state by viewModel.uiState.collectAsStateWithLifecycle()
	val context = LocalContext.current

	viewModel.effects.collectWithLifecycle {
		when (it) {
			is DetailEffect.GoToBackScreen -> onNavigateUp()
			is DetailEffect.ShowSnackBar -> onShowSnackBar(it.message, it.action)
			is DetailEffect.ShareResortWebUrl -> {
				val intent = Intent(Intent.ACTION_SEND).apply {
					type = "text/plain"
					putExtra(
						Intent.EXTRA_TEXT,
						"${WebMobileData.WEB_MOBILE_URL}${WebMobileData.WEBCAM_PARAM}/${state.resortWebKey}"
					)
				}
				context.startActivity(Intent.createChooser(intent, "${state.resortName}을 공유해보세요!"))
			}
		}

	}

	LaunchedEffect(Unit) {
		viewModel.handleEvent(
			DetailEvent.Init(resortId)
		)
	}

	Box(
		modifier = Modifier.fillMaxSize()
	) {
		Image(
			modifier = Modifier.fillMaxSize(),
			painter = painterResource(id = R.drawable.img_background),
			contentDescription = "",
			contentScale = ContentScale.FillBounds
		)

//        WindBlownSnowflakeEffect()

		DetailScreen(
			state = state,
			onAction = viewModel::handleEvent,
			modifier = Modifier
                .fillMaxSize()
                .padding(padding)
		)
	}
}

@Composable
internal fun DetailScreen(
	state: DetailState,
	modifier: Modifier = Modifier,
	onAction: (DetailEvent) -> Unit = {},
) {
	val lazyListState = rememberLazyListState()
	var cardOffset by remember { mutableStateOf(0f) }

	LazyColumn(
		modifier = modifier.fillMaxSize(),
		state = lazyListState
	) {
		item {
			WeskiHeader(
				showBackButton = true,
				showShareButton = true,
				onClickBackButton = { onAction(DetailEvent.ClickBackButton) },
				onShare = {
					onAction(DetailEvent.ClickShareButton)
				}
			)
		}

		item {
			Box(
				modifier = Modifier.fillMaxSize()
			) {
				DetailContent(
					state = state,
					onAction = onAction,
					measureWeatherCardPosition = {
						cardOffset = it
					}
				)
			}
		}
	}
}

@Composable
internal fun DetailContent(
	state: DetailState,
	modifier: Modifier = Modifier,
	onAction: (DetailEvent) -> Unit = {},
	measureWeatherCardPosition: (Float) -> Unit,
) {
	Column(
		modifier = modifier
			.fillMaxSize()
	) {
		Box(
			modifier = Modifier.padding(vertical = 28.dp, horizontal = 21.dp)
		) {
			DiscoverCard(
				modifier = Modifier.onGloballyPositioned {
					measureWeatherCardPosition(it.positionInRoot().y)
				},
				resortName = state.resortName,
				operatingSlopeCount = 5,
				status = "",
				currentTemperature = state.temperature,
				weatherCondition = state.weatherCondition,
			)
		}

		DetailViewPagerWithTab(
			state = state,
			submitSnowQualitySurvey = {
				onAction(DetailEvent.SubmitSnowQualitySurvey(it))
			},
			onShowSnackBar = { message, action ->
				onAction(DetailEvent.ShowSnackBar(message, action))
			}
		)
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun DetailScreenPreview() {
	DetailScreen(
		state = DetailState(
			resortId = 0,
			resortName = "용평스키장 모나",
			resortWebKey = SkiResortWebKey.EDEN,
			temperature = 7,
			weatherCondition = WeatherCondition.SNOW,
			weatherDescription = "눈이 내립니다.",
			snowQualitySurveyResult = SnowQualitySurveyResult(10, 5, 3, "")
		)
	)
}