package com.dieski.weski.presentation.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data class Detail(
        val resortId: Int,
        val resortName: String,
        val resortWebKey: String,
        val temperature: Int,
        val weatherType: String,
        val weatherDescription: String
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
