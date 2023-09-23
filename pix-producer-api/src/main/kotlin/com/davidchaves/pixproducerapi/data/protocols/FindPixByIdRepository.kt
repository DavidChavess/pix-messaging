package com.davidchaves.pixproducerapi.data.protocols

import java.math.BigDecimal
import java.time.LocalDateTime

enum class PixByIdStatus {
    PENDING,
    FINISHED,
    ERROR
}

data class PixById(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val transferDate: LocalDateTime,
    val status: PixByIdStatus,
)

interface FindPixByIdRepository {
    fun findById(id: String): PixById
}
