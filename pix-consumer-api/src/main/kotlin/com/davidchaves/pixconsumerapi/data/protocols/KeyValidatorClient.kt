package com.davidchaves.pixconsumerapi.data.protocols

interface KeyValidatorClient {
    fun valid(sourceKey: String, targetKey: String)
}
