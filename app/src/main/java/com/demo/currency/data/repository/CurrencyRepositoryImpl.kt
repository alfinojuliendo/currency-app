package com.demo.currency.data.repository

import com.demo.currency.data.database.CurrencyDao
import com.demo.currency.data.mapper.toDomain
import com.demo.currency.data.mapper.toEntity
import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    private val dao: CurrencyDao
) : CurrencyRepository {

    override fun insertCurrencyList(currencyList: List<CurrencyInfo>) {
        return dao.insertCurrencyList(currencyList.map { it.toEntity() })
    }

    override suspend fun getCurrencyList(): List<CurrencyInfo> {
        return dao.getCurrencyList().map { it.toDomain() }
    }

    override suspend fun getSortedCurrenciesByName(): List<CurrencyInfo> {
        return dao.getSortedCurrenciesByName().map { it.toDomain() }
    }
}
