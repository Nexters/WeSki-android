package com.dieski.remote

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
interface RemoteMapper<DataModel> {
	fun toData(): DataModel
}

internal fun <RemoteModel : RemoteMapper<DataModel>, DataModel> List<RemoteModel>.toData(): List<DataModel> {
	return map(RemoteMapper<DataModel>::toData)
}