package com.dieski.domain.model.platform

/**
 *
 * @author   JGeun
 * @created  2025/02/09
 */
enum class PlatformType(
	val value: String
) {
	ANDROID("ANDROID"),
	NONE("NONE");

	companion object {
		fun findByValue(value: String): PlatformType =
			entries.firstOrNull { it.value.equals(value, true) } ?: NONE
	}
}