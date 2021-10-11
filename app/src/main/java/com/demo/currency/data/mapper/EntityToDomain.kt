package com.demo.currency.data.mapper

import com.demo.currency.domain.model.CurrencyInfo
import com.demo.currency.domain.model.CurrencyInfoEntity

/*
* Mapper from data model to domain model
* */
fun CurrencyInfoEntity.toDomain(): CurrencyInfo {
    return CurrencyInfo(
        id = id,
        name = name!!,
        symbol = symbol!!
    )
}

