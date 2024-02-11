package com.eliseev.cleanapp.domain.usecase

import com.eliseev.cleanapp.domain.model.PricePetrol
import com.eliseev.cleanapp.domain.repository.PricePetrolRepository

class GetPricePetrol(val userRepository: PricePetrolRepository) {

    fun execute(): PricePetrol {
        val currentValue = userRepository.getPrice().value
        return PricePetrol(value = currentValue)
    }
}