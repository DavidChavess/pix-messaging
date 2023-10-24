package com.davidchaves.pixconsumerapi.domain

import java.math.BigDecimal

data class PixMessage(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val status: String
)

interface PixValidator {
    suspend fun process(pixMessage: PixMessage)
}