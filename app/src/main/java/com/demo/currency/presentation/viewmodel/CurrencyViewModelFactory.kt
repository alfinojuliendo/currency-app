package com.demo.currency.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.currency.domain.usecase.GetCurrencyListUseCase
import com.demo.currency.domain.usecase.GetSortedCurrencyListUseCase
import com.demo.currency.domain.usecase.InsertCurrencyUseCase

class CurrencyViewModelFactory(
    private val insertCurrencyUseCase: InsertCurrencyUseCase,
    private val getCurrencyListUseCase: GetCurrencyListUseCase,
    private val getSortedCurrencyListUseCase: GetSortedCurrencyListUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyViewModelImpl(
            insertCurrencyUseCase,
            getCurrencyListUseCase,
            getSortedCurrencyListUseCase
        ) as T
    }
}
