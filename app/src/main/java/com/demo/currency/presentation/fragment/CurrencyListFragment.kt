package com.demo.currency.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.demo.currency.R
import com.demo.currency.databinding.FragmentCurrencyListBinding
import com.demo.currency.di.CurrencyInjector
import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.presentation.adapter.CurrencyAdapter
import com.demo.currency.presentation.viewmodel.CurrencyViewModel

class CurrencyListFragment : Fragment(R.layout.fragment_currency_list) {

    private val viewModel: CurrencyViewModel by activityViewModels { defaultViewModelProviderFactory }

    private lateinit var binding: FragmentCurrencyListBinding

    private lateinit var adapter: CurrencyAdapter

    companion object {
        fun newInstance() = CurrencyListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CurrencyInjector.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrencyListBinding.bind(view)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.currencies.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                showRecyclerView(it)
            } else {
                hideRecyclerView()
            }
        }
    }

    private fun showRecyclerView(currencyList: List<CurrencyInfo>) {
        adapter = CurrencyAdapter({ currencyName ->
            Toast.makeText(context, "Item clicked: $currencyName", Toast.LENGTH_SHORT).show()
        })
        adapter.items = currencyList
        binding.run {
            rvCurrency.adapter = adapter
            rvCurrency.visibility = View.VISIBLE
        }
    }

    private fun hideRecyclerView() {
        binding.rvCurrency.visibility = View.GONE
        Toast.makeText(context, "Failed to load currency", Toast.LENGTH_SHORT).show()
    }
}
