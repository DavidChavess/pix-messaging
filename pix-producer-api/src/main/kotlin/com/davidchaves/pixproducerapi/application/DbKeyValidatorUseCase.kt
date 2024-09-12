package com.davidchaves.pixproducerapi.application

import com.davidchaves.pixproducerapi.application.DbKeyValidatorUseCase.Input
import com.davidchaves.pixproducerapi.application.DbKeyValidatorUseCase.Output
import com.davidchaves.pixproducerapi.application.protocols.KeyValidatorRepository
import com.davidchaves.pixproducerapi.application.protocols.UseCase

class DbKeyValidatorUseCase(
    private val keyValidatorRepository: KeyValidatorRepository
) : UseCase<Input, Output> {

    data class Input(
        val targetKey: String,
        val sourceKey: String,
    )

    data class Output(
        val isValid: Boolean
    )

    override fun execute(input: Input): Output {
        return Output(keyValidatorRepository.valid(input.sourceKey, input.targetKey))
    }
}