package com.demo.currency.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.demo.currency.databinding.AdapterItemCurrencyBinding
import com.demo.currency.domain.model.CurrencyInfo

class CurrencyAdapter(
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    var items = listOf<CurrencyInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AdapterItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is CurrencyViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class CurrencyViewHolder(val binding: AdapterItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick(items[adapterPosition].name)
            }
        }

        fun bind(position: Int) {
            binding.run {
                tvCurrencyIcon.text = items[position].name.first().toString()
                tvCurrencyName.text = items[position].name
                tvCurrencyLabel.text = items[position].symbol
            }
        }
    }
}
