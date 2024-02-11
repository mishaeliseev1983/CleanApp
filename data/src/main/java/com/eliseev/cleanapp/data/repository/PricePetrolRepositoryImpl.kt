package com.eliseev.cleanapp.data.repository

import android.content.Context
import com.eliseev.cleanapp.domain.model.PricePetrol
import com.eliseev.cleanapp.domain.repository.PricePetrolRepository


private const val SHARED_PREFS_PETROL = "shared_prefs_petrol"
private const val KEY_PRICE_PETROL = "KEY_PRICEPETROL"
private const val DEFAULT_PRICE_PETROL = 10L

class PricePetrolRepositoryImpl(context: Context) : PricePetrolRepository {

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_PETROL, Context.MODE_PRIVATE
    )

    override fun savePrice(savePricePetrol: PricePetrol): Boolean {
        sharedPreferences.edit().putLong(KEY_PRICE_PETROL, savePricePetrol.value).apply()
        return true
    }

    override fun getPrice(): PricePetrol {
        val current = sharedPreferences.getLong(KEY_PRICE_PETROL, DEFAULT_PRICE_PETROL)
        return PricePetrol(value = current)
    }

    override fun getHistoryInfo(): List<String> =
        listOf("BP 1995 год - 6 рублей",
            "1996 год 7 рублей",
            "1997 год - 9 рублей",
            "ЛУКОЙЛ 1998 год - 10 рублей",
            "Юкос 2002 год - 11 рублей",
            "Газпром-Нефть 2005 год - 12 рублей",
            "2000 год - 12 рублей",
            "2001 год - 12 рублей",
            "РОСнефть2008 - 20 рублей",
            "ГАЗПРОМНЕФТЬ 2009 год - 22 рублей",
            "2013 год - 28 рублей",
            "2014 год - 29 рублей",
            "2016 год - 32 рублей",
            "2017 год - 33 рублей",)
}