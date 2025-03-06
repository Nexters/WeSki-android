package com.dieski.domain.usecase

import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.repository.WeSkiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
class GetSkiResortListUseCase @Inject constructor(
	private val weSkiRepository: WeSkiRepository
) {
	operator fun invoke(): Flow<List<SkiResortInfo>> =
		weSkiRepository.getSkiResortList()
}