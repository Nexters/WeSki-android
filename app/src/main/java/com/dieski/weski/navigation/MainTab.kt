package com.dieski.weski.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.navigation.MainTabRoute
import com.dieski.weski.presentation.core.navigation.Route

internal enum class MainTab(
    @DrawableRes val iconResId: Int,
    val tabText: String,
    internal val contentDescription: String,
    val route: MainTabRoute
) {
    WEATHER(
        iconResId = R.drawable.ic_launcher_background,
        tabText = "날씨",
        contentDescription = "날씨",
        route = MainTabRoute.Weather
    ),
    HOME(
        iconResId = R.drawable.ic_launcher_background,
        tabText = "웹캠",
        contentDescription = "웹캠",
        route = MainTabRoute.HOME
    ),
    CONGESTION(
        iconResId = R.drawable.ic_launcher_background,
        tabText = "혼잡도",
        contentDescription = "혼잡도",
        route = MainTabRoute.Congestion
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}