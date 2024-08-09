package com.dieski.weski.presentation.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data class Detail(
        val skiResortName: String
    ) :Route
}


/*
sealed interface MainTabRoute : Route {
    @Serializable
    data object Weather : MainTabRoute

    @Serializable
    data object HOME : MainTabRoute

    @Serializable
    data object Congestion : MainTabRoute
}*/
