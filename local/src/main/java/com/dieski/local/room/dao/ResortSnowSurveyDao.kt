package com.dieski.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dieski.local.entity.ResortSnowSurveyEntity

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
@Dao
interface ResortSnowSurveyDao {

	@Query("SELECT * FROM snow_survey")
	suspend fun getAllSurveys(): List<ResortSnowSurveyEntity>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveSurvey(
		resortSnowSurveyEntity: ResortSnowSurveyEntity
	)

	@Query("SELECT * FROM snow_survey WHERE resort_id = :resortId")
	suspend fun getSurveyByResortId(resortId: Long): ResortSnowSurveyEntity?

	@Query("SELECT * FROM snow_survey WHERE resort_id IN (:resortIdList)")
	suspend fun getSurveysByResortIds(
		resortIdList: List<Long>
	): List<ResortSnowSurveyEntity>

	@Delete
	suspend fun deleteSurvey(
		resortSnowSurveyEntity: ResortSnowSurveyEntity
	)

	@Query("DELETE FROM snow_survey WHERE resort_id IN (:resortIdList)")
	suspend fun deleteSurveysByResortIds(
		resortIdList: List<Long>
	)
}