package com.demo.currency.di

import com.demo.currency.application.BaseApplication

object CurrencyInjector {
    lateinit var component: CurrencyComponent
        private set

    fun initialize(application: BaseApplication) {
        if (!CurrencyInjector::component.isInitialized) {
            component = DaggerCurrencyComponent.builder()
                .currencyModule(CurrencyModule(application))
                .build()
        }
    }
}
