package com.dieski.weski.presentation.core.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author   JGeun
 * @created  2025/11/22
 */
fun isDatePassed(dateString: String, pattern: String = "yyyy-MM-dd"): Boolean = try {
	val formatter = DateTimeFormatter.ofPattern(pattern)
	val targetDate = LocalDate.parse(dateString, formatter)
	val today = LocalDate.now()

	targetDate.isAfter(today)
} catch (e: Exception) {
	false
}

fun skiResortOpeningDatePassed(openingDateString: String, pattern: String = "yyyy-MM-dd"): Boolean = try {
	val formatter = DateTimeFormatter.ofPattern(pattern)
	val targetDate = LocalDate.parse(openingDateString, formatter)
	val today = LocalDate.now()

	targetDate.isAfter(today).not()
} catch (e: Exception) {
	false
}