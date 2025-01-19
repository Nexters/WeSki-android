package com.dieski.weski.presentation.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

	@Serializable
	data object Home : Route

	@Serializable
	data class Detail(val resortId: Long) : Route

	@Serializable
	data class ResortWebcamUrlView(
		val resortId: Long,
		val resortName: String,
		val url: String
	) : Route
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
