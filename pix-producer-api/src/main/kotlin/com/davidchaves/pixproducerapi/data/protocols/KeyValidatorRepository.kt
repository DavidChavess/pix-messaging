package com.davidchaves.pixproducerapi.data.protocols

interface KeyValidatorRepository {
    fun valid(sourceKey: String, targetKey: String): Boolean
}
