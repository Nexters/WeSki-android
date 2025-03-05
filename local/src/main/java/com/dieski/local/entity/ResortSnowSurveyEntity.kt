package com.dieski.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dieski.data.model.ResortSnowSurveyDto
import com.dieski.local.LocalMapper
import com.dieski.local.room.RoomConstant

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
@Entity(tableName = RoomConstant.Table.SNOW_SURVEY)
data class ResortSnowSurveyEntity(
	@PrimaryKey
	@ColumnInfo("resort_id")
	val resortId: Long,

	@ColumnInfo("submit_date")
	val submitDate: String
): LocalMapper<ResortSnowSurveyDto> {
	override fun toData(): ResortSnowSurveyDto =
		ResortSnowSurveyDto(
			resortId,
			submitDate
		)
}

fun ResortSnowSurveyDto.toLocal(): ResortSnowSurveyEntity =
	ResortSnowSurveyEntity(
		resortId,
		submitDate
	)