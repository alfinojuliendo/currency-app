package com.demo.currency.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.currency.domain.model.CurrencyInfoEntity

@Database(
    entities = [CurrencyInfoEntity::class],
    version = 1
)
abstract class CurrencyDatabase : RoomDatabase() {

    companion object {
        internal const val DATABASE_NAME = "currency_app.db"
    }

    abstract fun getCurrencyDao(): CurrencyDao
}
