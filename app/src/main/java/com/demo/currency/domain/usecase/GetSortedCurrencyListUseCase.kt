package com.demo.currency.domain.usecase

import com.demo.currency.presentation.wrapper.ResultLoad
import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.repository.CurrencyRepository
import com.demo.currency.safeDataLoad
import kotlinx.coroutines.CoroutineDispatcher

interface GetSortedCurrencyListUseCase {
    suspend operator fun invoke(): ResultLoad<List<CurrencyInfo>>
}

class GetSortedCurrencyListUseCaseImpl(
    private val repository: CurrencyRepository,
    private val dispatcher: CoroutineDispatcher
) : GetSortedCurrencyListUseCase {
    override suspend fun invoke(): ResultLoad<List<CurrencyInfo>> {
        return safeDataLoad(dispatcher) {
            repository.getSortedCurrenciesByName()
        }
    }
}
