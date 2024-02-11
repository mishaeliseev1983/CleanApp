package com.eliseev.cleanapp.domain.usecase

import com.eliseev.cleanapp.domain.model.PricePetrol
import com.eliseev.cleanapp.domain.repository.PricePetrolRepository

class GetHistoryInfo(val userRepository: PricePetrolRepository) {

    fun execute(): List<String> {
        return userRepository.getHistoryInfo()
    }
}