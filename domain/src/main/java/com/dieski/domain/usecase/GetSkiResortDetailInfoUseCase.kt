package com.dieski.domain.usecase

import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import com.dieski.domain.model.SkiResortDetailInfo
import com.dieski.domain.model.result.DetailError
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSkiResortDetailInfoUseCase @Inject constructor(
    private val getAllSkiResortsUseCase: GetAllSkiResortsUseCase,
    private val getWeatherInfoUseCase: GetSkiResortWeatherInfoUseCase,
    private val getSnowQualitySurveyResultUseCase: GetSnowQualitySurveyResultUseCase,
    @Dispatcher(WeSkiDispatchers.DEFAULT) private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        resortId: Long,
    ): WResult<SkiResortDetailInfo, WError> = withContext(defaultDispatcher) {
        val allSkiResortsResultDeferred = async { getAllSkiResortsUseCase() }
        val skiResultWeatherInfoResultDeferred = async { getWeatherInfoUseCase(resortId) }
        val snowQualitySurveyResultDeferred = async { getSnowQualitySurveyResultUseCase(resortId) }

        val allSkiResortsResult = allSkiResortsResultDeferred.await()
        val skiResultWeatherInfoResult = skiResultWeatherInfoResultDeferred.await()
        val snowQualitySurveyResult = snowQualitySurveyResultDeferred.await()

        if (allSkiResortsResult is WResult.Success && skiResultWeatherInfoResult is WResult.Success && snowQualitySurveyResult is WResult.Success) {
            val skiResortInfo = allSkiResortsResult.data.firstOrNull { it.resortId == resortId }
            if (skiResortInfo != null) {
                WResult.Success(
                    SkiResortDetailInfo(
                        skiResortInfo,
                        skiResultWeatherInfoResult.data,
                        snowQualitySurveyResult.data
                    )
                )
            } else {
                WResult.Error(DetailError("resortId에 해당하는 스키장이 없습니다."))
            }
        } else if (allSkiResortsResult is WResult.Error) {
            WResult.Error(allSkiResortsResult.error)
        } else if (skiResultWeatherInfoResult is WResult.Error) {
            WResult.Error(skiResultWeatherInfoResult.error)
        } else if (snowQualitySurveyResult is WResult.Error) {
            WResult.Error(snowQualitySurveyResult.error)
        } else {
            WResult.Error(DetailError("알 수 없는 에러가 발생했습니다."))
        }
    }
}