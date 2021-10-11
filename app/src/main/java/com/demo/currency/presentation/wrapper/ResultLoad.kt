package com.demo.currency.presentation.wrapper

sealed class ResultLoad<out T> {
    class Success<out T>(val data: T) : ResultLoad<T>()
    class Failure(val throwable: Throwable) : ResultLoad<Nothing>()

    /**
     * Peeking the data within ResultLoad wrapper
     * Only returns the data when the Result is Success
     * Most cases should prefer retrieving the data after Success to avoid nullability
     */
    val peekData: T? get() = when(this) {
        is Success -> data
        else       -> null
    }
}
