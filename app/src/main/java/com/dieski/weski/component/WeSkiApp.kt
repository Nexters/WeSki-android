package com.dieski.weski.component

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.weski.WeSkiAppState
import com.dieski.weski.navigation.MainNavHost
import com.dieski.weski.navigation.MainTab
import com.dieski.weski.presentation.R
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@Composable
internal fun WeSkiApp(
    appState: WeSkiAppState
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val localContextResource = LocalContext.current.resources
    val onShowErrorSnackBar: (throwable: Throwable?) -> Unit = { throwable ->
        appState.coroutineScope.launch {
            snackBarHostState.showSnackbar(
                when(throwable) {
                    is UnknownHostException -> localContextResource.getString(R.string.error_message_network)
                    else -> localContextResource.getString(R.string.error_message_unknown)
                }
            )
        }
    }

    MainScreenContent(
        appState = appState,
        snackBarHostState = snackBarHostState,
        onShowErrorSnackBar = onShowErrorSnackBar,
    )
}

@Composable
private fun MainScreenContent(
    appState: WeSkiAppState,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    LaunchedEffect(isOffline) {
        if (isOffline) snackBarHostState.showSnackbar("offline 상태입니다.")
    }

    Scaffold(
        modifier = modifier,
        content = { padding ->
            MainNavHost(
                navigator = appState.navigator,
                padding = padding,
                onShowErrorSnackBar = onShowErrorSnackBar
            )
        },
        bottomBar = {
            MainBottomBar(
                visible = appState.navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toPersistentList(),
                currentTab = appState.navigator.currentTab,
                onTabSelected = { appState.navigator.navigate(it) },
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(start = 8.dp, end = 8.dp, bottom = 28.dp),
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    )
}