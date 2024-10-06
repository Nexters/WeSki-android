package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/10/06
 */
enum class SkiResortWebKey(
	val serverResortId: Long,
	val value: String
) {
	JISAN(1, "jisan"),
	GONJIAM(2, "gonjiam"),
	VIVALDIPARK(3, "vivaldipark"),
	ELYSIAN_GANGCHON(4, "elysian-gangchon"),
	WELLIHILLI(5, "wellihilli"),
	PHOENIX(6, "phoenix"),
	HIGH1(7, "high1"),
	YONGPYONG(8, "yongpyong"),
	MUJU(9, "muju"),
	EDEN(10, "eden"),
	O2(11, "o2"),
	NONE(-1, "none");

	companion object {
		fun findByServerResortId(resortId: Long) =
			entries.firstOrNull { it.serverResortId == resortId } ?: NONE
	}
}