package com.demo.currency.di

import androidx.room.Room
import com.demo.currency.application.BaseApplication
import com.demo.currency.data.database.CurrencyDao
import com.demo.currency.data.database.CurrencyDatabase
import com.demo.currency.data.repository.CurrencyRepositoryImpl
import com.demo.currency.domain.repository.CurrencyRepository
import com.demo.currency.domain.usecase.GetCurrencyListUseCase
import com.demo.currency.domain.usecase.GetCurrencyListUseCaseImpl
import com.demo.currency.domain.usecase.GetSortedCurrencyListUseCase
import com.demo.currency.domain.usecase.GetSortedCurrencyListUseCaseImpl
import com.demo.currency.domain.usecase.InsertCurrencyUseCase
import com.demo.currency.domain.usecase.InsertCurrencyUseCaseImpl
import com.demo.currency.presentation.viewmodel.CurrencyViewModel
import com.demo.currency.presentation.viewmodel.CurrencyViewModelImpl
import com.demo.currency.presentation.viewmodel.CurrencyViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class CurrencyModule(private val application: BaseApplication) {

    @Provides
    @CurrencyScope
    fun provideCurrencyDatabase(): CurrencyDatabase {
        return Room.databaseBuilder(
            application,
            CurrencyDatabase::class.java,
            CurrencyDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @CurrencyScope
    fun provideCurrencyViewModel(
        insertCurrencyUseCase: InsertCurrencyUseCase,
        getCurrencyListUseCase: GetCurrencyListUseCase,
        getSortedCurrencyListUseCase: GetSortedCurrencyListUseCase
    ): CurrencyViewModel {
        return CurrencyViewModelImpl(
            insertCurrencyUseCase,
            getCurrencyListUseCase,
            getSortedCurrencyListUseCase
        )
    }

    @Provides
    @CurrencyScope
    fun provideCurrencyViewModelFactory(
        insertCurrencyUseCase: InsertCurrencyUseCase,
        getCurrencyListUseCase: GetCurrencyListUseCase,
        getSortedCurrencyListUseCase: GetSortedCurrencyListUseCase
    ): CurrencyViewModelFactory {
        return CurrencyViewModelFactory(
            insertCurrencyUseCase,
            getCurrencyListUseCase,
            getSortedCurrencyListUseCase
        )
    }

    @Provides
    fun provideCurrencyDao(currencyDatabase: CurrencyDatabase): CurrencyDao {
        return currencyDatabase.getCurrencyDao()
    }

    @Provides
    @CurrencyScope
    fun provideCurrencyRepository(
        currencyDao: CurrencyDao
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(currencyDao)
    }

    @Provides
    @CurrencyScope
    fun provideInsertCurrencyUseCase(
        currencyRepository: CurrencyRepository
    ): InsertCurrencyUseCase {
        return InsertCurrencyUseCaseImpl(currencyRepository, Dispatchers.IO)
    }

    @Provides
    @CurrencyScope
    fun provideGetCurrencyListUseCase(
        currencyRepository: CurrencyRepository
    ): GetCurrencyListUseCase {
        return GetCurrencyListUseCaseImpl(currencyRepository, Dispatchers.IO)
    }

    @Provides
    @CurrencyScope
    fun provideGetSortedCurrencyListUseCase(
        currencyRepository: CurrencyRepository
    ): GetSortedCurrencyListUseCase {
        return GetSortedCurrencyListUseCaseImpl(currencyRepository, Dispatchers.IO)
    }
}
