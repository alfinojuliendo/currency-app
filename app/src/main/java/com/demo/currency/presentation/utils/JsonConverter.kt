package com.demo.currency.presentation.utils

import android.content.Context

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ex: Exception) {
        ex.printStackTrace()
        return null
    }
    return jsonString
}
