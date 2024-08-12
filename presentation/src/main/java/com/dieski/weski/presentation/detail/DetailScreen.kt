package com.dieski.weski.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.weski.presentation.core.designsystem.component.LoadingIndicator
import com.dieski.weski.presentation.core.designsystem.discover.DiscoverCard
import com.dieski.weski.presentation.core.designsystem.header.WeskiHeader
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
	onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
	viewModel: DetailViewModel = hiltViewModel()
) {

	val state by viewModel.uiState.collectAsStateWithLifecycle()

	LaunchedEffect(Unit) {
		viewModel.handleEvent(DetailContract.Event.FetchingSkiResortData(skiResortName))
	}

	DetailScreen(
		state = state,
		onShowErrorSnackBar = onShowErrorSnackBar,
		modifier = Modifier
			.fillMaxSize()
			.padding(padding)
	)
}

@Composable
internal fun DetailScreen(
	modifier: Modifier = Modifier,
	state: DetailContract.State = DetailContract.State.Loading,
	onShowErrorSnackBar: (throwable: Throwable?) -> Unit = {}
) {
	Column(
		modifier = modifier
	) {
		WeskiHeader(
			showBackButton = true,
			showShareButton = true
		)

		Box(
			modifier = modifier.fillMaxSize()
		) {
			if (state is DetailContract.State.Success) {
				DetailContent()
			} else {
				LoadingIndicator()
			}
		}

	}

}

@Composable
internal fun DetailContent(
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier
			.fillMaxSize()
	) {
		Box(
			modifier = Modifier.padding(vertical = 28.dp, horizontal = 21.dp)
		) {
			DiscoverCard()
		}
		
		DetailViewPagerWithTab()

		DetailSnowQualitySurvey()
	}
}