package com.eliseev.cleanapp.domain.usecase

import com.eliseev.cleanapp.domain.model.PricePetrol
import com.eliseev.cleanapp.domain.repository.PricePetrolRepository

class SavePricePetrol(val userRepository: PricePetrolRepository) {

    fun execute(savePricePetrol: PricePetrol): Boolean {

        if (savePricePetrol.value < 0) return false
        val oldValue = userRepository.getPrice()
        if (oldValue.value == savePricePetrol.value) return false

        return userRepository.savePrice(
            savePricePetrol = PricePetrol(value = savePricePetrol.value)
        )
    }
}