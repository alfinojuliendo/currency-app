package com.demo.currency.domain.repository

import com.demo.currency.domain.model.CurrencyInfo

interface CurrencyRepository {

    fun insertCurrencyList(currencyList: List<CurrencyInfo>)

    suspend fun getCurrencyList(): List<CurrencyInfo>

    suspend fun getSortedCurrenciesByName(): List<CurrencyInfo>
}
