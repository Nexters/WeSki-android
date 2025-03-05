package com.dieski.remote

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
interface RemoteMapper<DataModel> {
	fun toData(): DataModel
}