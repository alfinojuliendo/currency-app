package com.demo.currency.domain.usecase

import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.repository.CurrencyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface InsertCurrencyUseCase {
    suspend operator fun invoke(currencyList: List<CurrencyInfo>)
}

class InsertCurrencyUseCaseImpl(
    private val repository: CurrencyRepository,
    private val dispatcher: CoroutineDispatcher
) : InsertCurrencyUseCase {
    override suspend fun invoke(currencyList: List<CurrencyInfo>) {
        return withContext(dispatcher) {
            repository.insertCurrencyList(currencyList)
        }
    }
}
