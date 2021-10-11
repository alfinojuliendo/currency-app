package com.demo.currency.data.mapper

import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.model.CurrencyInfoEntity

/*
* Mapper from domain model to data model
* */
fun CurrencyInfo.toEntity(): CurrencyInfoEntity {
    return CurrencyInfoEntity(
        id = id,
        name = name,
        symbol = symbol
    )
}
