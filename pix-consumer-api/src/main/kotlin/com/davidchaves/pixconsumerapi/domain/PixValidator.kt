package com.davidchaves.pixconsumerapi.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class PixMessage(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val status: String,
    val transferDate: LocalDateTime
) {
    fun isPending(): Boolean  = status == "PENDING"
    fun isFinished(): Boolean  = status == "FINISHED"
}

interface PixValidator {
    suspend fun process(pixMessage: PixMessage)
}