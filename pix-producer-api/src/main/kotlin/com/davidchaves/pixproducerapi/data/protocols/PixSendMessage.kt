package com.davidchaves.pixproducerapi.data.protocols

import java.math.BigDecimal
import java.time.LocalDateTime

enum class PixMessageStatus {
    PENDING,
    FINISHED,
    ERROR
}

data class PixMessage(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val transferDate: LocalDateTime,
    val status: PixMessageStatus,
)

interface PixSendMessage {
    fun send(pix: PixMessage): PixMessage
}
