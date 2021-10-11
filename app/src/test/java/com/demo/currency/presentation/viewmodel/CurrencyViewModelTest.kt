package com.demo.currency.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.usecase.GetCurrencyListUseCase
import com.demo.currency.domain.usecase.GetSortedCurrencyListUseCase
import com.demo.currency.domain.usecase.InsertCurrencyUseCase
import com.demo.currency.presentation.wrapper.ResultLoad
import com.demo.currency.presentation.wrapper.ResultLoad.Failure
import com.demo.currency.presentation.wrapper.ResultLoad.Success
import com.demo.currency.utils.CoroutineTestRule
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.lang.Exception

@ExperimentalCoroutinesApi
class CurrencyViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewmodel: CurrencyViewModelImpl
    private lateinit var insertCurrencyUseCase: InsertCurrencyUseCase
    private lateinit var getCurrencyListUseCase: GetCurrencyListUseCase
    private lateinit var getSortedCurrencyListUseCase: GetSortedCurrencyListUseCase

    private val modelList = listOf(
        CurrencyInfo("Crypto", "Crypto.com", "MCO"),
        CurrencyInfo("Bitcoin", "Bitcoin", "BTC")
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        insertCurrencyUseCase = getFakeInsertCurrencyUsecase()
        getCurrencyListUseCase = getFakeGetCurrencyListUseCase(Success(modelList))
        getSortedCurrencyListUseCase =
            getFakeGetSortedCurrencyListUseCase(Success(modelList.sortedBy { it.name }))
        initializeViewModel()
    }

    @Test
    fun testInsertCurrencies_shouldInsertCurrencyList() {
        // When
        viewmodel.insertCurrencies(modelList)

        // Then
        verify(viewmodel).insertCurrencies(modelList)
    }

    @Test
    fun testLoadCurrencies_whenResultIsSuccess_shouldUpdateCurrenciesValue() {
        // When
        viewmodel.loadCurrencies()

        // Then
        assertEquals(viewmodel.currencies.value, modelList)
    }

    @Test
    fun testLoadCurrencies_whenResultIsFailure_shouldReturnEmptyList() {
        // Given
        getCurrencyListUseCase = getFakeGetCurrencyListUseCase(Failure(Exception()))
        initializeViewModel()

        // When
        viewmodel.loadCurrencies()

        // Then
        assertEquals(viewmodel.currencies.value, emptyList<CurrencyInfo>())
    }

    @Test
    fun testLoadSortedCurrenciesByName_whenResultIsSuccess_shouldUpdateCurrenciesValue() {
        // When
        viewmodel.loadSortedCurrenciesByName()

        // Then
        assertEquals(viewmodel.currencies.value, modelList.sortedBy { it.name })
    }

    @Test
    fun testLoadSortedCurrenciesByName_whenResultIsFailure_shouldReturnEmptyList() {
        // Given
        getSortedCurrencyListUseCase = getFakeGetSortedCurrencyListUseCase(Failure(Exception()))
        initializeViewModel()

        // When
        viewmodel.loadSortedCurrenciesByName()

        // Then
        assertEquals(viewmodel.currencies.value, emptyList<CurrencyInfo>())
    }

    private fun initializeViewModel() {
        viewmodel = spy(
            CurrencyViewModelImpl(
                insertCurrencyUseCase,
                getCurrencyListUseCase,
                getSortedCurrencyListUseCase
            )
        )
    }

    // region fake usecase
    private fun getFakeInsertCurrencyUsecase() = object : InsertCurrencyUseCase {
        override suspend fun invoke(currencyList: List<CurrencyInfo>): ResultLoad<Unit> {
            return Success(Unit)
        }
    }

    private fun getFakeGetCurrencyListUseCase(result: ResultLoad<List<CurrencyInfo>>) =
        object : GetCurrencyListUseCase {
            override suspend fun invoke(): ResultLoad<List<CurrencyInfo>> {
                return result
            }
        }

    private fun getFakeGetSortedCurrencyListUseCase(result: ResultLoad<List<CurrencyInfo>>) =
        object : GetSortedCurrencyListUseCase {
            override suspend fun invoke(): ResultLoad<List<CurrencyInfo>> {
                return result
            }
        }
    // endregion
}
