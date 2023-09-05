package com.demo.currency.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.currency.domain.model.CurrencyInfoEntity

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencyList(currency: List<CurrencyInfoEntity>)

    @Query("SELECT * FROM currency")
    suspend fun getCurrencyList(): List<CurrencyInfoEntity>

    @Query("SELECT * FROM currency ORDER BY name ASC")
    suspend fun getSortedCurrenciesByName(): List<CurrencyInfoEntity>

       @Query("SELECT * FROM currency ORDER BY name ASC")
    suspend fun getSortedCurrenciesByName(): List<CurrencyInfoEntity>

       @Query("SELECT * FROM currency ORDER BY name ASC")
    suspend fun getSortedCurrenciesByName(): List<CurrencyInfoEntity>

       @Query("SELECT * FROM currency ORDER BY name ASC")
    suspend fun getSortedCurrenciesByName(): List<CurrencyInfoEntity>

       @Query("SELECT * FROM currency ORDER BY name ASC")
    suspend fun getSortedCurrenciesByName(): List<CurrencyInfoEntity>

       @Query("SELECT * FROM currency ORDER BY name ASC")
    suspend fun getSortedCurrenciesByName(): List<CurrencyInfoEntity>

       @Query("SELECT * FROM currency ORDER BY name ASC")
    suspend fun getSortedCurrenciesByName(): List<CurrencyInfoEntity>
}
