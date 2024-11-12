package com.dieski.data.repository.mapper

import com.dieski.domain.model.ResortSnowMakerSurveyRecord
import com.dieski.local.entity.ResortSnowMakerSurveyRecordEntity

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
fun ResortSnowMakerSurveyRecordEntity.toDomain() = ResortSnowMakerSurveyRecord(
	resortId = resortId,
	submitDate = submitDate
)