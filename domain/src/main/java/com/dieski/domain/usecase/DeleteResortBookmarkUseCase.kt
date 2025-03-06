package com.dieski.domain.usecase

import com.dieski.domain.repository.WeSkiRepository
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/11/30
 */
class DeleteResortBookmarkUseCase @Inject constructor(
	private val weSkiRepository: WeSkiRepository
){
	suspend operator fun invoke(
		resortId: Long
	): Result<Unit> = weSkiRepository.deleteResortBookmark(resortId)
}