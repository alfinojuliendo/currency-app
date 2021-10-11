package com.demo.currency.domain.usecase

import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.repository.CurrencyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class InsertCurrencyUseCaseImplTest {

    private lateinit var usecase: InsertCurrencyUseCase

    @Test
    fun testGetSortedCurrencyList_shouldReturnFromRepository() {
        runBlockingTest {
            // Given
            val modelList = listOf(
                CurrencyInfo("Crypto", "Crypto.com", "MCO"),
                CurrencyInfo("Bitcoin", "Bitcoin", "BTC")
            )
            val fakeRepository = getFakeRepository(modelList)
            usecase = InsertCurrencyUseCaseImpl(fakeRepository, TestCoroutineDispatcher())

            // When
            usecase.invoke(modelList)

            // Then
            assertEquals(fakeRepository.getCurrencyList(), modelList)
        }
    }

    private fun getFakeRepository(currencyList: List<CurrencyInfo>) =
        object : CurrencyRepository {
            override fun insertCurrencyList(currencyList: List<CurrencyInfo>) {}

            override suspend fun getCurrencyList(): List<CurrencyInfo> {
                return currencyList
            }

            override suspend fun getSortedCurrenciesByName(): List<CurrencyInfo> {
                return currencyList.sortedBy { it.name }
            }
        }
}
