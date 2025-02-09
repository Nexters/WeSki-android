package com.dieski.weski.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dieski.domain.model.platform.PlatformType
import com.dieski.domain.model.platform.PlatformVersion
import com.dieski.domain.usecase.CheckPlatformVersionForForcedUpdate
import com.dieski.weski.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/02/10
 */
@HiltViewModel
class IntroViewModel  @Inject constructor(
	private val checkPlatformVersionForForcedUpdate: CheckPlatformVersionForForcedUpdate
) : ViewModel() {

	val checkRequiredForceUpdateVersion: StateFlow<Boolean?> =
		checkPlatformVersionForForcedUpdate(
			platformType = PlatformType.ANDROID,
			platformVersion = PlatformVersion(BuildConfig.VERSION_NAME)
		).stateIn(
			scope = viewModelScope,
			started = SharingStarted.WhileSubscribed(5_000),
			initialValue = null
		)
}