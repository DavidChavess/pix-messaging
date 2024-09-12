package com.davidchaves.pixproducerapi.domain.event

import com.davidchaves.pixproducerapi.domain.entity.Pix.Status
import java.math.BigDecimal
import java.time.LocalDateTime

data class PixPendingEvent(
    val id: String,
    val targetKey: String,
    val sourceKey: String,
    val value: BigDecimal,
    val transferDate: LocalDateTime,
    val status: Status,
    override val eventName: String
) : DomainEvent