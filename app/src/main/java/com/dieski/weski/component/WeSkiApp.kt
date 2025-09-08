package com.dieski.weski.component

import android.webkit.WebView
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dieski.weski.WeSkiAppState
import com.dieski.weski.navigation.MainNavHost
import com.dieski.weski.presentation.core.LocalWebOwner
import com.dieski.weski.presentation.model.WeSkiEnterScreenLoggerRoute
import com.dieski.weski.presentation.onboarding.OnboardingOpenNotiRoute2025
import com.dieski.weski.presentation.util.log
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
internal fun WeSkiApp(
    appState: WeSkiAppState,
) {
    val currentDate = LocalDate.now()
    val cutoffDate = LocalDate.of(2025, 11, 29)

    if (currentDate.isBefore(cutoffDate)) {
        OnboardingOpenNotiRoute2025()
    } else {
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

        val navBackStackEntry: NavBackStackEntry? by appState.navigator.navController.currentBackStackEntryAsState()
        val currentDestination: NavDestination? by remember(navBackStackEntry) { derivedStateOf { navBackStackEntry?.destination } }
        var previousLoggingScreenEventName: String? by remember { mutableStateOf(null) }

        LaunchedEffect(currentDestination) {
            val weSkiEnterScreenLoggerRoute = WeSkiEnterScreenLoggerRoute
                .createWeSkiEnterScreenLoggerRoute(currentDestination)

            val screenLoggerEventName = weSkiEnterScreenLoggerRoute.getEventName()

            if(screenLoggerEventName.isNullOrEmpty().not() &&
                checkNotNull(screenLoggerEventName) != previousLoggingScreenEventName
             ) {
                val localDateTime: LocalDateTime = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
                val formattedDateTime = localDateTime.format(formatter)

                appState.logger.log(screenLoggerEventName, formattedDateTime)
                previousLoggingScreenEventName = screenLoggerEventName
            }
        }

        MainScreenContent(
            appState = appState,
            snackBarHostState = snackBarHostState,
            onShowSnackBar = onShowSnackBar,
        )
    }
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