package com.demo.currency.domain.usecase

import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.repository.CurrencyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCurrencyListUseCaseImplTest {

    private lateinit var usecase: GetCurrencyListUseCase

    @Test
    fun testGetCurrencyList_shouldReturnFromRepository() {
        runBlockingTest {
            // Given
            val modelList = listOf(
                CurrencyInfo("Crypto", "Crypto.com", "MCO"),
                CurrencyInfo("Bitcoin", "Bitcoin", "BTC")
            )
            val fakeRepository = getFakeRepository(modelList)
            usecase = GetCurrencyListUseCaseImpl(fakeRepository, TestCoroutineDispatcher())

            // When
            val result = usecase.invoke()

            // Then
            assertEquals(result.peekData, modelList)
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
