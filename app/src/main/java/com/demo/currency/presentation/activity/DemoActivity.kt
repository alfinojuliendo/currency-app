package com.demo.currency.presentation.activity

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.demo.currency.R
import com.demo.currency.databinding.ActivityDemoBinding
import com.demo.currency.di.CurrencyInjector
import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.presentation.fragment.CurrencyListFragment
import com.demo.currency.presentation.utils.getJsonDataFromAsset
import com.demo.currency.presentation.viewmodel.CurrencyViewModel
import com.demo.currency.presentation.viewmodel.CurrencyViewModelFactory
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import javax.inject.Inject

class DemoActivity : AppCompatActivity() {

    @Inject
    lateinit var currencyViewModelFactory: CurrencyViewModelFactory

    private val viewModel: CurrencyViewModel by viewModels { currencyViewModelFactory }

    private lateinit var binding: ActivityDemoBinding

    private val jsonFileName = "currency_list.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrencyInjector.component.inject(this)
        insertData()
        setupView()
    }

    private fun setupView() {
        binding = ActivityDemoBinding.inflate(layoutInflater)
        binding.run {
            setContentView(root)
            btnLoadData.setOnClickListener {
                viewModel.loadCurrencies()
                showFragment()
            }
            btnSortData.setOnClickListener {
                viewModel.loadSortedCurrenciesByName()
                showFragment()
            }
        }
    }

    private fun insertData() {
        val jsonString = getJsonDataFromAsset(this, jsonFileName)
        val type = object : TypeToken<List<CurrencyInfo>>() {}.type
        val currencyList: List<CurrencyInfo> = Gson().fromJson(jsonString, type)
        viewModel.insertCurrencies(currencyList)
    }

    private fun showFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutContainer, CurrencyListFragment.newInstance())
            .commit()
    }
}
