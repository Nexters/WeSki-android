package com.dieski.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dieski.local.entity.ResortSnowMakerSurveyRecordEntity

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
@Dao
interface ResortSnowMakerSurveyRecordDao {

	@Query("SELECT * FROM ResortSnowMakerSurveyRecord")
	suspend fun getAllResortSnowMakerSurveyRecord(): List<ResortSnowMakerSurveyRecordEntity>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveResortSnowMakerSurveyRecord(
		resortSnowMakerSurveyRecordEntity: ResortSnowMakerSurveyRecordEntity
	)

	@Query("SELECT * FROM ResortSnowMakerSurveyRecord WHERE resort_id = :resortId")
	suspend fun findSnowMakerSurveyRecord(
		resortId: Long
	): ResortSnowMakerSurveyRecordEntity?

	@Query("SELECT * FROM ResortSnowMakerSurveyRecord WHERE resort_id IN (:resortIdList)")
	suspend fun findSnowMakerSurveyRecordList(
		resortIdList: List<Long>
	): List<ResortSnowMakerSurveyRecordEntity>

	@Delete
	suspend fun deleteResortSnowMakerSurveyRecord(
		resortSnowMakerSurveyRecordEntity: ResortSnowMakerSurveyRecordEntity
	)

	@Query("DELETE FROM ResortSnowMakerSurveyRecord WHERE resort_id IN (:resortIdList)")
	suspend fun deleteResortSnowMakerSurveyRecordList(
		resortIdList: List<Long>
	)
}