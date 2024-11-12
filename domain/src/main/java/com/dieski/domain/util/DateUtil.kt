package com.dieski.domain.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
object DateUtil {
	fun createYYYYMMDDFormat(): String {
		val currentDate = LocalDate.now()
		val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
		return currentDate.format(formatter)
	}
}
