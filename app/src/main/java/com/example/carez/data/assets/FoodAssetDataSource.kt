package com.example.carez.data

import android.content.Context
import com.example.carez.domain.model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FoodAssetDataSource(private val context: Context) {
    fun loadFoodsFromAssets(): List<Food> {
        val json = context.assets.open("foods.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Food>>() {}.type
        return Gson().fromJson(json, type)
    }
}
