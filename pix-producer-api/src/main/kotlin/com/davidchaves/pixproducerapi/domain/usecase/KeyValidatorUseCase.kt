package com.davidchaves.pixproducerapi.domain.usecase

interface KeyValidatorUseCase {
    fun valid(sourceKey: String, targetKey: String): Boolean
}