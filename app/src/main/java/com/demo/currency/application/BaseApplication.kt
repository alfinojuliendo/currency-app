package com.demo.currency.application

import android.app.Application
import com.demo.currency.di.CurrencyInjector

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CurrencyInjector.initialize(this)
    }
}
