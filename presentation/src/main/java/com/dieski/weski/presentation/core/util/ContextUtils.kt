package com.dieski.weski.presentation.core.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * @author   JGeun
 * @created  2025/11/01
 */
internal fun Context.findActivity(): Activity? {
	var context = this
	while (context is ContextWrapper) {
		if (context is Activity) {
			return context
		}
		context = context.baseContext
	}
	return null
}