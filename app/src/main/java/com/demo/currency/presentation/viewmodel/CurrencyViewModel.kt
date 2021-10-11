package com.demo.currency.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.currency.presentation.wrapper.ResultLoad.Success
import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.usecase.GetCurrencyListUseCase
import com.demo.currency.domain.usecase.GetSortedCurrencyListUseCase
import com.demo.currency.domain.usecase.InsertCurrencyUseCase
import kotlinx.coroutines.launch

interface CurrencyViewModel {
    val currencies: LiveData<List<CurrencyInfo>>

    fun insertCurrencies(currencyList: List<CurrencyInfo>)

    fun loadCurrencies()

    fun loadSortedCurrenciesByName()
}

class CurrencyViewModelImpl(
    private val insertCurrencyUseCase: InsertCurrencyUseCase,
    private val getCurrencyListUseCase: GetCurrencyListUseCase,
    private val getSortedCurrencyListUseCase: GetSortedCurrencyListUseCase
) : CurrencyViewModel, ViewModel() {

    override val currencies = MutableLiveData<List<CurrencyInfo>>()

    override fun insertCurrencies(currencyList: List<CurrencyInfo>) {
        viewModelScope.launch {
            insertCurrencyUseCase.invoke(currencyList)
        }
    }

    override fun loadCurrencies() {
        viewModelScope.launch {
            currencies.value = when (val result = getCurrencyListUseCase.invoke()) {
                is Success -> result.data
                else       -> emptyList()
            }
        }
    }

    override fun loadSortedCurrenciesByName() {
        viewModelScope.launch {
            currencies.value = when (val result = getSortedCurrencyListUseCase.invoke()) {
                is Success -> result.data
                else       -> emptyList()
            }
        }
    }
}
