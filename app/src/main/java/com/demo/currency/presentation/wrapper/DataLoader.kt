package com.demo.currency

import com.demo.currency.presentation.wrapper.ResultLoad
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> safeDataLoad(
    dispatchers: CoroutineDispatcher,
    data: suspend() -> T
): ResultLoad<T> {
    return withContext(dispatchers) {
        try {
            ResultLoad.Success(data.invoke())
        } catch (throwable: Throwable) {
            ResultLoad.Failure(throwable)
        }
    }
}
