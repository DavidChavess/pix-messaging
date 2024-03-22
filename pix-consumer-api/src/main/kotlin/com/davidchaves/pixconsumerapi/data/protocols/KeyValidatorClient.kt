package com.davidchaves.pixconsumerapi.data.protocols

interface KeyValidatorClient {
    suspend fun valid(sourceKey: String, targetKey: String)
}
