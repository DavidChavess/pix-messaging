package com.davidchaves.pixproducerapi.application.protocols

interface KeyValidatorRepository {
    fun valid(sourceKey: String, targetKey: String): Boolean
}
