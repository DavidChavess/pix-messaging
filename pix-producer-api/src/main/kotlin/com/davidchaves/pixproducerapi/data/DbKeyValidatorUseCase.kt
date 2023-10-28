package com.davidchaves.pixproducerapi.data

import com.davidchaves.pixproducerapi.data.protocols.KeyValidatorRepository
import com.davidchaves.pixproducerapi.domain.usecase.KeyValidatorUseCase

class DbKeyValidatorUseCase(
    private val keyValidatorRepository: KeyValidatorRepository
) : KeyValidatorUseCase {

    override fun valid(sourceKey: String, targetKey: String): Boolean =
        keyValidatorRepository.valid(sourceKey, targetKey)
}