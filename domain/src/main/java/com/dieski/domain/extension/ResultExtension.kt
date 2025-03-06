package com.dieski.domain.extension

import kotlin.coroutines.cancellation.CancellationException

/**
 *
 * @author   JGeun
 * @created  2025/03/06
 */
suspend inline fun <R> runSuspendCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        Result.failure(e)
    }
}