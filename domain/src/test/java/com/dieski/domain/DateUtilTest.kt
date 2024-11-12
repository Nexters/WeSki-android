package com.dieski.domain

import com.dieski.domain.util.DateUtil
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
class DateUtilTest {
	@Test
	fun createYYYYMMDDFormat() {
		val date = DateUtil.createYYYYMMDDFormat()
		assertEquals("2024/11/12", date)
	}
}