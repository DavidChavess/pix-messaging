package com.davidchaves.pixproducerapi.data.protocols

import java.math.BigDecimal
import java.time.LocalDateTime

enum class PixStatus {
    PENDING,
    FINISHED,
    ERROR
}

data class CreatePix(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val transferDate: LocalDateTime,
    val status: PixStatus,
)

interface CreatePixRepository {
    fun add(pix: CreatePix): CreatePix
}
