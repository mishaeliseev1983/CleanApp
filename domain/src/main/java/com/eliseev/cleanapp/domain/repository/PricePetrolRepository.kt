package com.eliseev.cleanapp.domain.repository

import com.eliseev.cleanapp.domain.model.PricePetrol

interface PricePetrolRepository {
    fun savePrice(savePricePetrol: PricePetrol): Boolean
    fun getPrice(): PricePetrol
    fun getHistoryInfo(): List<String>
}