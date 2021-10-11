package com.demo.currency.presentation.wrapper

sealed class ResultLoad<out T> {
    class Success<out T>(val data: T) : ResultLoad<T>()
    class Failure(val throwable: Throwable) : ResultLoad<Nothing>()
}
