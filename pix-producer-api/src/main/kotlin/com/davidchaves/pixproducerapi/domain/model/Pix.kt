package com.davidchaves.pixproducerapi.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime

enum class Status {
    PENDING,
    FINISHED,
    ERROR
}

data class Pix(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val transferDate: LocalDateTime,
    val status: Status,
)
