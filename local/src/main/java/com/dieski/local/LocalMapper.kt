package com.dieski.local

/**
 * @author   JGeun
 * @created  2025/03/05
 */
interface LocalMapper<DataModel> {
	fun toData(): DataModel
}

fun <LocalModel: LocalMapper<DataModel>, DataModel> List<LocalModel>.toData(): List<DataModel> {
	return map {  it.toData() }
}