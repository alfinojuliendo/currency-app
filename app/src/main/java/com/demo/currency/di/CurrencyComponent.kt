package com.demo.currency.di

import com.demo.currency.presentation.activity.DemoActivity
import com.demo.currency.presentation.fragment.CurrencyListFragment
import dagger.Component
import javax.inject.Scope

@Component(
    modules = [
        CurrencyModule::class
    ]
)

@CurrencyScope
interface CurrencyComponent {
    fun inject(demoActivity: DemoActivity)

    fun inject(currencyListFragment: CurrencyListFragment)
}

@Scope
@Retention
annotation class CurrencyScope
