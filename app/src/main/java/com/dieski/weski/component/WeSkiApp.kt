package com.dieski.weski.component

import android.webkit.WebView
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.weski.WeSkiAppState
import com.dieski.weski.navigation.MainNavHost
import com.dieski.weski.presentation.core.LocalWebOwner
import kotlinx.coroutines.launch

@Composable
internal fun WeSkiApp(
    appState: WeSkiAppState
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val onShowSnackBar: (String, String?) -> Unit = { message, action ->
        appState.coroutineScope.launch {
            snackBarHostState.showSnackbar(
                message = message,
                actionLabel = action,
                duration = Short
            )
        }
    }

    MainScreenContent(
        appState = appState,
        snackBarHostState = snackBarHostState,
        onShowSnackBar = onShowSnackBar,
    )
}

@Composable
private fun MainScreenContent(
    appState: WeSkiAppState,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onShowSnackBar: (String, String?) -> Unit,
) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(isOffline) {
        if (isOffline) snackBarHostState.showSnackbar("offline 상태입니다.")
    }

    CompositionLocalProvider(
        LocalWebOwner provides WebView(context)
    ) {
        Scaffold(
            modifier = modifier,
            content = { padding ->
                MainNavHost(
                    navigator = appState.navigator,
                    padding = padding,
                    onShowSnackBar = onShowSnackBar
                )
            },
            snackbarHost = { SnackbarHost(snackBarHostState) }
        )
    }
}