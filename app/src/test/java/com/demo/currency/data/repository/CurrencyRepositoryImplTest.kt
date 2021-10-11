package com.demo.currency.data.repository

import com.demo.currency.data.database.CurrencyDao
import com.demo.currency.data.mapper.toEntity
import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.model.CurrencyInfoEntity
import com.demo.currency.domain.repository.CurrencyRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyRepositoryImplTest {

    private lateinit var repository: CurrencyRepository

    @Test
    fun testInsertCurrencyList_shouldInsertCurrencyList() {
        runBlocking {
            // Given
            val modelList = listOf(
                CurrencyInfo("Crypto", "Crypto.com", "MCO"),
                CurrencyInfo("Bitcoin", "Bitcoin", "BTC")
            )
            val fakeDao = getFakeDao(modelList.map { it.toEntity() })
            repository = CurrencyRepositoryImpl(fakeDao)

            // When
            repository.insertCurrencyList(modelList)

            // Then
            val result = repository.getCurrencyList()
            assertEquals(result, modelList)
        }
    }

    @Test
    fun testGetCurrencyList_shouldReturnCurrencyInfoList() {
        runBlocking {
            // Given
            val modelList = listOf(
                CurrencyInfo("Crypto", "Crypto.com", "MCO"),
                CurrencyInfo("Bitcoin", "Bitcoin", "BTC")
            )
            val fakeDao = getFakeDao(modelList.map { it.toEntity() })
            repository = CurrencyRepositoryImpl(fakeDao)

            // When
            val result = repository.getCurrencyList()

            // Then
            assertEquals(result, modelList)
        }
    }

    @Test
    fun testGetSortedCurrenciesByName_shouldReturnSortedCurrencyInfoList() {
        runBlocking {
            // Given
            val modelList = listOf(
                CurrencyInfo("Crypto", "Crypto.com", "MCO"),
                CurrencyInfo("Bitcoin", "Bitcoin", "BTC")
            )
            val fakeDao = getFakeDao(modelList.map { it.toEntity() })
            repository = CurrencyRepositoryImpl(fakeDao)

            // When
            val result = repository.getSortedCurrenciesByName()

            // Then
            assertEquals(result, modelList.sortedBy { it.name })
        }
    }

    private fun getFakeDao(
        currencyList: List<CurrencyInfoEntity>
    ) = object : CurrencyDao {
        override fun insertCurrencyList(currency: List<CurrencyInfoEntity>) {}

        override suspend fun getCurrencyList(): List<CurrencyInfoEntity> {
            return currencyList
        }

        override suspend fun getSortedCurrenciesByName(): List<CurrencyInfoEntity> {
            return currencyList.sortedBy { it.name }
        }
    }
}
