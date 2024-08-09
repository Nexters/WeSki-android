package com.dieski.weski.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.weski.presentation.core.designsystem.component.LoadingIndicator
import com.dieski.weski.presentation.core.designsystem.component.LogoBar
import com.dieski.weski.presentation.home.component.HomeMessageBadge
import com.dieski.weski.presentation.home.component.HomeWeatherCard
import com.dieski.weski.presentation.home.model.HomeWeatherUiModel

@Composable
internal fun HomeRouter(
    padding: PaddingValues,
    navigateToDetail: (HomeWeatherUiModel) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state: HomeContract.State by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleEvent(HomeContract.Event.FetchingHomeData)
    }

    HomeScreen(
        onShowErrorSnackBar = onShowErrorSnackBar,
        state = state,
        navigateToDetail = navigateToDetail,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeContract.State = HomeContract.State.Loading,
    navigateToDetail: (HomeWeatherUiModel) -> Unit = {},
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit = {},
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (state is HomeContract.State.Success) {
            HomeContent(
                weatherList = state.weatherList,
                onCardClick = navigateToDetail,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            LoadingIndicator()
        }
    }

}

@Composable
internal fun HomeContent(
    weatherList: List<HomeWeatherUiModel>,
    modifier: Modifier = Modifier,
    onCardClick: (HomeWeatherUiModel) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LogoBar(
            onBackButtonShow = false,
            onShareButtonShow = false,
            share = {},
            navigateUp = {}
        )

        Spacer(Modifier.height(16.dp))

        HomeMessageBadge()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                weatherList
            ) { weather ->
                key(weather.id) {
                    HomeWeatherCard(
                        weatherUiModel = weather,
                        onCardClick = { onCardClick(weather) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}