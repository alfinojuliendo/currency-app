package com.demo.currency.domain.usecase

import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.repository.CurrencyRepository
import com.demo.currency.presentation.wrapper.ResultLoad
import com.demo.currency.safeDataLoad
import kotlinx.coroutines.CoroutineDispatcher

interface InsertCurrencyUseCase {
    suspend operator fun invoke(currencyList: List<CurrencyInfo>): ResultLoad<Unit>
}

class InsertCurrencyUseCaseImpl(
    private val repository: CurrencyRepository,
    private val dispatcher: CoroutineDispatcher
) : InsertCurrencyUseCase {
    override suspend fun invoke(currencyList: List<CurrencyInfo>): ResultLoad<Unit> {
        return safeDataLoad(dispatcher) {
            repository.insertCurrencyList(currencyList)
        }
    }
}
